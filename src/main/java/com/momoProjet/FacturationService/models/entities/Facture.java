package com.momoProjet.FacturationService.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;
    @Column
    private double taux_imposition;
    @Column
    private double montant;
    @ManyToMany
    private List <Comptes> comptes;
    @ManyToMany
    private List<ProprioFacture> proprio;

    @ManyToOne
    private Facture_Compagnie facture_compagnie;

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

    public List<Comptes> getComptes() {
        return comptes;
    }

    public void setComptes(List<Comptes> comptes) {
        this.comptes = comptes;
    }

    public List<ProprioFacture> getProprio() {
        return proprio;
    }

    public void setProprio(List<ProprioFacture> proprio) {
        this.proprio = proprio;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Facture getFactureAssocie() {
        return (Facture_Compagnie)facture_compagnie;
    }

    public void setFactureAssocie(Facture_Compagnie facture_compagnie) {
        this.facture_compagnie = facture_compagnie;
    }
}
