package com.momoProjet.FacturationService.models.DTO;

import com.momoProjet.FacturationService.models.entities.Facture;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class ComptesDTO {
    private long id;

    private double revenuTotal;

    private List<FactureDTO> factures;

    private ProprioFactureDTO proprioFacture;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRevenuTotal() {
        return revenuTotal;
    }

    public void setRevenuTotal(double revenuTotal) {
        this.revenuTotal = revenuTotal;
    }

    public List<FactureDTO> getFactures() {
        return factures;
    }

    public void setFactures(List<FactureDTO> factures) {
        this.factures = factures;
    }

    public ProprioFactureDTO getProprioFacture() {
        return proprioFacture;
    }

    public void setProprioFacture(ProprioFactureDTO proprioFacture) {
        this.proprioFacture = proprioFacture;
    }
}
