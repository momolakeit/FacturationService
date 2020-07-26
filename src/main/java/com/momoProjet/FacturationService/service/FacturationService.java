package com.momoProjet.FacturationService.service;

import com.momoProjet.FacturationService.models.DTO.ComptesDTO;
import com.momoProjet.FacturationService.models.DTO.FactureDTO;
import com.momoProjet.FacturationService.models.DTO.Facture_CompagnieDTO;
import com.momoProjet.FacturationService.models.DTO.ProprioFactureDTO;
import com.momoProjet.FacturationService.models.entities.Comptes;
import com.momoProjet.FacturationService.models.entities.Facture;
import com.momoProjet.FacturationService.models.entities.Facture_Compagnie;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import com.momoProjet.FacturationService.models.mapper.*;
import com.momoProjet.FacturationService.repositories.ComptesRepository;
import com.momoProjet.FacturationService.repositories.FactureRepository;
import com.momoProjet.FacturationService.repositories.ProprioFactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class FacturationService {
    private final double pourcentageEmployes=0.4;
    private final ComptesRepository comptesRepository;
    private final FactureRepository factureRepository;
    private final ProprioFactureRepository proprioFactureRepository;
    @Autowired
    public FacturationService(ComptesRepository comptesRepository,
                              FactureRepository factureRepository,
                              ProprioFactureRepository proprioFactureRepository) {
        this.comptesRepository = comptesRepository;
        this.factureRepository = factureRepository;
        this.proprioFactureRepository = proprioFactureRepository;

    }

    public ComptesDTO findCompteById(Long id){
        Comptes comptes=comptesRepository.findById(id).get();
        ComptesDTO comptesDTO = ComptesToComptesDTO.instance.convert(comptes);
        comptesDTO.setFactures(convertItem(new ArrayList<>(),FactureToFactureDTO.instance,comptes.getFactures()));
        comptesDTO.setProprioFacture(ProprioFactureToProprioFactureDTO.instance.convert(comptes.getProprioFacture()));

        return comptesDTO;
    }
    public FactureDTO findFactureById(Long id){
        Facture facture = (Facture) factureRepository.findById(id).get();
        FactureDTO factureDTO = FactureToFactureDTO.instance.convert(facture);
        factureDTO.setFacture_compagnie(Facture_CompagnieToFacture_CompagnieDTO.instance.convert((Facture_Compagnie) facture.getFactureAssocie()));
        factureDTO.setProprio(convertItem(new ArrayList<>(),ProprioFactureToProprioFactureDTO.instance,facture.getProprio()));
        factureDTO.setComptes(convertItem(new ArrayList<>(),ComptesToComptesDTO.instance,facture.getComptes()));

        return factureDTO;
    }
    public FactureDTO creerFacture(List<ProprioFacture> proprioFactureDTOList,Facture facture,double montant){
        facture.setTaux_imposition(0.15);
        facture.setComptes(new ArrayList<>());
        facture.setProprio(new ArrayList<>());
        facture.setMontant(montant);
        for(ProprioFacture proprioFactureDTO:proprioFactureDTOList){
            ProprioFacture proprioFacture =proprioFactureRepository.findByEmail(proprioFactureDTO.getEmail());

            facture.getProprio().add(proprioFacture);
            facture.getComptes().add(proprioFacture.getComptes());

            Comptes comptes =proprioFacture.getComptes();
            comptes.setRevenuTotal(comptes.getRevenuTotal()+facture.getMontant());

            proprioFacture =proprioFactureRepository.save(proprioFacture);
            comptes =comptesRepository.save(comptes);
            if(Objects.nonNull(comptes.getFactures())){
                comptes.getFactures().add(facture);
            }
            else{
                comptes.setFactures(addItem(new ArrayList<>(),facture));
            }
            if(Objects.nonNull(proprioFacture.getFactures())){
                proprioFacture.getFactures().add(facture);
            }
            else{
                proprioFacture.setFactures(addItem(new ArrayList<>(),facture));
            }
            factureRepository.save(facture);
            proprioFactureRepository.save(proprioFacture);
            comptesRepository.save(comptes);


        }
        FactureDTO retour = new FactureDTO();
        retour =FactureToFactureDTO.instance.convert(facture);
        retour.setComptes(convertItem(new ArrayList<>(),ComptesToComptesDTO.instance,facture.getComptes()));

        retour.setProprio(convertItem(new ArrayList<>(),ProprioFactureToProprioFactureDTO.instance,facture.getProprio()));


        return retour;
    }
    public Facture_CompagnieDTO creerFactureCompagnie(ProprioFactureDTO proprioFactureDTO,
                                                      Facture_Compagnie facture_compagnie)  {
        ProprioFacture proprioFacture =proprioFactureRepository.findByEmail(proprioFactureDTO.getEmail());
        List <Facture> factures = proprioFacture.getFactures();
        Comptes comptes= proprioFacture.getComptes();
        double revenuBrut=0;
        for(Facture facture: factures){
            revenuBrut= revenuBrut+facture.getMontant();
        }
        facture_compagnie.setRevenu_brut(revenuBrut);
        double paieEmployes=revenuBrut*pourcentageEmployes;
        comptes.setRevenuTotal(comptes.getRevenuTotal()-paieEmployes);
        facture_compagnie.setPaie_employes(paieEmployes);
        facture_compagnie.setRevenu_net(revenuBrut-paieEmployes);
        facture_compagnie.setFactureAssocie(factures);
        facture_compagnie.setComptes(addItem(new ArrayList<>(),comptes));
        facture_compagnie.setProprio(addItem(new ArrayList<>(),proprioFacture));
        factureRepository.save(facture_compagnie);
        Facture_CompagnieDTO retour = Facture_CompagnieToFacture_CompagnieDTO.instance.convert(facture_compagnie);

        retour.setProprio(convertItem(  new ArrayList<ProprioFactureDTO>(),
                                        ProprioFactureToProprioFactureDTO.instance,
                                        facture_compagnie.getProprio()));

        retour.setComptes(convertItem(new ArrayList<ComptesDTO>(),ComptesToComptesDTO.instance,facture_compagnie.getComptes()));
        return  retour;
    }
    public FactureDTO modiferFacture (FactureDTO factureDTO) {
        Facture facture= (Facture) factureRepository.findById(factureDTO.getId()).get();
        FactureDTO dto =FactureToFactureDTO.instance.convert(facture);
        dto.setProprio(convertItem(new ArrayList<>(),
                        ProprioFactureToProprioFactureDTO.instance,
                        facture.getProprio()));
        factureRepository.save(facture);
        return dto;
    }

    private <T,Q> List<T> convertItem(List<T> arrayList, MapperInterface converter,List<Q> objToConvert){
        for(Q x:objToConvert){
            arrayList.add((T)converter.convert(x));
        }
        return arrayList;
    }
    private <T> List<T> addItem(List<T> list,T item){
        list.add(item);
        return list;
    }




}
