package com.momoProjet.FacturationService.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Facture_Compagnie extends Facture {
    /**liste de compte avec juste un pour faciliter heritage**/
    @Column
    private double revenu_brut;
    @Column
    private double paie_employes;
    @Column
    private double revenu_net;

    @OneToMany
    private List<Facture> factures;


    public double getRevenu_brut() {
        return revenu_brut;
    }

    public void setRevenu_brut(double revenu_brut) {
        this.revenu_brut = revenu_brut;
    }

    public double getPaie_employes() {
        return paie_employes;
    }

    public void setPaie_employes(double paie_employes) {
        this.paie_employes = paie_employes;
    }

    public double getRevenu_net() {
        return revenu_net;
    }

    public void setRevenu_net(double revenu_net) {
        this.revenu_net = revenu_net;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactureAssocie(List<Facture> factures) {
        this.factures = factures;
    }
}
