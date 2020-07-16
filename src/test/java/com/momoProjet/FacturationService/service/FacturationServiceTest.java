package com.momoProjet.FacturationService.service;

import com.momoProjet.FacturationService.models.DTO.ComptesDTO;
import com.momoProjet.FacturationService.models.DTO.FactureDTO;
import com.momoProjet.FacturationService.models.DTO.Facture_CompagnieDTO;
import com.momoProjet.FacturationService.models.DTO.ProprioFactureDTO;
import com.momoProjet.FacturationService.models.entities.Comptes;
import com.momoProjet.FacturationService.models.entities.Facture;
import com.momoProjet.FacturationService.models.entities.Facture_Compagnie;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import com.momoProjet.FacturationService.repositories.ComptesRepository;
import com.momoProjet.FacturationService.repositories.FactureRepository;
import com.momoProjet.FacturationService.repositories.ProprioFactureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class FacturationServiceTest {
    private FacturationService facturationService;

    @Mock
    private ComptesRepository comptesRepository;

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private ProprioFactureRepository proprioFactureRepository;
    @Test
    public void creerFactureRetourneRetourneBonFacture(){
        facturationService =new FacturationService(comptesRepository,factureRepository,proprioFactureRepository);
        List<ProprioFacture> proprioFactures=new ArrayList<>();
        ProprioFacture proprioFacture1 =new ProprioFacture();

        proprioFacture1.setEmail("business@gmail.com");

        proprioFactures.add(proprioFacture1);

        Comptes compte1=new Comptes();
        compte1.setProprioFacture(proprioFacture1);
        proprioFacture1.setComptes(compte1);


        Facture facture =new Facture();
        when(comptesRepository.save(any(Comptes.class))).thenReturn(null);
        when(proprioFactureRepository.findByEmail(any(String.class))).thenReturn(proprioFacture1);
        when(proprioFactureRepository.save(any(ProprioFacture.class))).thenReturn(null);
        when(factureRepository.save(any(Facture.class))).thenReturn(facture);
        FactureDTO returnValue = facturationService.creerFacture(proprioFactures,facture,50);
        assertEquals(50,facture.getMontant());
        assertTrue(proprioFacture1.getEmail().contentEquals(facture.getProprio().get(0).getEmail()));
        assertEquals(proprioFacture1.getComptes().getFactures().get(0).getMontant()
                   ,facture.getMontant());
        assertEquals(facture.getComptes().get(0).getRevenuTotal(),50);


    }
    @Test
    public void creerFactureCompagnie() throws InvocationTargetException, IllegalAccessException {
        facturationService =new FacturationService(comptesRepository,factureRepository,proprioFactureRepository);
        ProprioFacture proprioFacture1 =new ProprioFacture();
        ProprioFactureDTO proprioFactureDTO = new ProprioFactureDTO();

        proprioFacture1.setEmail("business@gmail.com");
        proprioFactureDTO.setEmail("business@gmail.com");
        Facture factureProprio=new Facture();
        FactureDTO factureProprioDTO = new FactureDTO();
        factureProprio.setMontant(100);
        factureProprioDTO.setMontant(100);
        proprioFacture1.setFactures(new ArrayList<>());
        proprioFactureDTO.setFactures(new ArrayList<>());
        proprioFacture1.getFactures().add(factureProprio);
        proprioFactureDTO.getFactures().add(factureProprioDTO);


        Comptes compte1=new Comptes();
        ComptesDTO comptesDTO=new ComptesDTO();
        compte1.setProprioFacture(proprioFacture1);
        proprioFacture1.setComptes(compte1);

        compte1.setRevenuTotal(100);
        comptesDTO.setRevenuTotal(100);


        Facture_Compagnie facture =new Facture_Compagnie();

        when(proprioFactureRepository.findByEmail(any(String.class))).thenReturn(proprioFacture1);
        when(factureRepository.save(any(Facture_Compagnie.class))).thenReturn(facture);
        Facture_CompagnieDTO returnValue = facturationService.creerFactureCompagnie(proprioFactureDTO,facture);
        assertEquals(60,facture.getRevenu_net());
        assertEquals(100,facture.getRevenu_brut());
        assertEquals(60,facture.getComptes().get(0).getRevenuTotal());



    }

}