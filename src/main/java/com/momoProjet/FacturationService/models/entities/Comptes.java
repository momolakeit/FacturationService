package com.momoProjet.FacturationService.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comptes {
    @Id
    private long id;
    @Column
    private double revenuTotal;

    @ManyToMany
    private List<Facture> factures;

    @OneToOne
    private ProprioFacture proprioFacture;

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

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public ProprioFacture getProprioFacture() {
        return proprioFacture;
    }

    public void setProprioFacture(ProprioFacture proprioFacture) {
        this.proprioFacture = proprioFacture;
    }
}
