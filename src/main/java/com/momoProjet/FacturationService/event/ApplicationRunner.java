package com.momoProjet.FacturationService.event;

import com.momoProjet.FacturationService.models.entities.Comptes;
import com.momoProjet.FacturationService.models.entities.Facture;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import com.momoProjet.FacturationService.repositories.ComptesRepository;
import com.momoProjet.FacturationService.repositories.FactureRepository;
import com.momoProjet.FacturationService.repositories.ProprioFactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
    @Autowired
    ProprioFactureRepository proprioFactureRepository;
    @Autowired
    ComptesRepository comptesRepository ;

    @Autowired
    FactureRepository factureRepository;
    @Override
    public void run(String... args) throws Exception {
        Comptes comptes = new Comptes();
        comptes.setRevenuTotal(50);
        ProprioFacture proprioFacture = new ProprioFacture();
        proprioFacture.setEmail("proprio1@gmail.com");

        comptes =comptesRepository.save(comptes);
        proprioFacture =proprioFactureRepository.save(proprioFacture);
        proprioFacture.setComptes(comptes);
        comptes.setProprioFacture(proprioFacture);
        comptesRepository.save(comptes);
        proprioFactureRepository.save(proprioFacture);
        Comptes comptes2 = new Comptes();
        comptes2.setRevenuTotal(505);
        ProprioFacture proprioFacture2 = new ProprioFacture();
        proprioFacture2.setEmail("proprio2@gmail.com");
        proprioFacture2 =proprioFactureRepository.save(proprioFacture2);
        comptes2 =comptesRepository.save(comptes2);
        proprioFacture2.setComptes(comptes);
        comptes2.setProprioFacture(proprioFacture2);
        proprioFactureRepository.save(proprioFacture2);
        comptesRepository.save(comptes2);
        Facture facture = new Facture();
        facture.setMontant(100);
        facture= (Facture) factureRepository.save(facture);
        String s;
    }
}
