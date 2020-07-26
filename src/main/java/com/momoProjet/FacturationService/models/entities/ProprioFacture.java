package com.momoProjet.FacturationService.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProprioFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;
    @Column
    private String email;

    @ManyToMany
    private List<Facture> factures;

    @OneToOne
    private Comptes comptes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> proprio) {
        this.factures = proprio;
    }

    public Comptes getComptes() {
        return comptes;
    }

    public void setComptes(Comptes comptes) {
        this.comptes = comptes;
    }
}
