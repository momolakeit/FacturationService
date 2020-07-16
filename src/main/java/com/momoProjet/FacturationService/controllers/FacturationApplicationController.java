package com.momoProjet.FacturationService.controllers;

import com.momoProjet.FacturationService.models.DTO.FactureDTO;
import com.momoProjet.FacturationService.models.DTO.Facture_CompagnieDTO;
import com.momoProjet.FacturationService.models.DTO.ProprioFactureDTO;
import com.momoProjet.FacturationService.models.entities.Facture;
import com.momoProjet.FacturationService.models.entities.Facture_Compagnie;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import com.momoProjet.FacturationService.service.FacturationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class FacturationApplicationController {
    @Autowired
    private FacturationService facturationService;


    @RequestMapping(path = "/creerFacture", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> creerFacture(List<ProprioFacture> proprioFactureDTOList, double montant){
       return ResponseEntity.ok(facturationService.creerFacture(proprioFactureDTOList,new Facture(),50));
    }

    @RequestMapping(path = "/creerFacture_Compagnie", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> creerFactureCompagmie(ProprioFactureDTO proprioFactureDTO){
        return ResponseEntity.ok(facturationService.creerFactureCompagnie(proprioFactureDTO,new Facture_Compagnie()));
    }

    @RequestMapping(path = "/findFactureById",method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> findById(Long id){
        return  ResponseEntity.ok(facturationService.findCompteById(id));
    }

    @RequestMapping(path = "/modifierFacture",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> modifierFacture(FactureDTO factureDTO){
        return  ResponseEntity.ok(facturationService.modiferFacture(factureDTO));
    }

}
