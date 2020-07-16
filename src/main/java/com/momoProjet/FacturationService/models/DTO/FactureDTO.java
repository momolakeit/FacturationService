package com.momoProjet.FacturationService.models.DTO;

import com.momoProjet.FacturationService.models.entities.Comptes;
import com.momoProjet.FacturationService.models.entities.Facture_Compagnie;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

public class FactureDTO {
    private long id;
    @Column
    private double taux_imposition;
    @Column
    private double montant;

    private List<ComptesDTO> comptes;

    private List<ProprioFactureDTO> proprio;


    private Facture_CompagnieDTO facture_compagnie;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTaux_imposition() {
        return taux_imposition;
    }

    public void setTaux_imposition(double taux_imposition) {
        this.taux_imposition = taux_imposition;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public List<ComptesDTO> getComptes() {
        return comptes;
    }

    public void setComptes(List<ComptesDTO> comptes) {
        this.comptes = comptes;
    }

    public List<ProprioFactureDTO> getProprio() {
        return proprio;
    }

    public void setProprio(List<ProprioFactureDTO> proprio) {
        this.proprio = proprio;
    }

    public Facture_CompagnieDTO getFacture_compagnie() {
        return facture_compagnie;
    }

    public void setFacture_compagnie(Facture_CompagnieDTO facture_compagnie) {
        this.facture_compagnie = facture_compagnie;
    }
}
