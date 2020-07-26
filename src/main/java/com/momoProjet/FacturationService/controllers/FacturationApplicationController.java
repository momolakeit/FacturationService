package com.momoProjet.FacturationService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoProjet.FacturationService.models.DTO.FactureDTO;
import com.momoProjet.FacturationService.models.DTO.ProprioFactureDTO;
import com.momoProjet.FacturationService.models.entities.Facture;
import com.momoProjet.FacturationService.models.entities.Facture_Compagnie;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import com.momoProjet.FacturationService.service.FacturationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class FacturationApplicationController {
    @Autowired
    private FacturationService facturationService;


    @RequestMapping(path = "/creerFacture", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> creerFacture(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ProprioFacture[] proprioFactureList =new ObjectMapper().readValue(json.get("proprioList"), ProprioFacture[].class);
        return ResponseEntity.ok(facturationService.creerFacture(Arrays.asList(proprioFactureList),new Facture(),Double.valueOf(json.get("montant"))));

    }

    @RequestMapping(path = "/creerFacture_Compagnie", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> creerFactureCompagmie(ProprioFactureDTO proprioFactureDTO){
        return ResponseEntity.ok(facturationService.creerFactureCompagnie(proprioFactureDTO,new Facture_Compagnie()));
    }

    @RequestMapping(path = "/findFactureById",method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> findById(@RequestBody Long id){
        return  ResponseEntity.ok(facturationService.findFactureById(id));
    }

    @RequestMapping(path = "/modifierFacture",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> modifierFacture(@RequestBody  FactureDTO factureDTO){
        return  ResponseEntity.ok(facturationService.modiferFacture(factureDTO));
    }

}
