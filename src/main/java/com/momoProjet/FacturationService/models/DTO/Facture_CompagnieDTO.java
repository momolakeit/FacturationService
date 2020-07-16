package com.momoProjet.FacturationService.models.DTO;

import com.momoProjet.FacturationService.models.entities.Facture;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

public class Facture_CompagnieDTO extends FactureDTO {
    /**liste de compte avec juste un pour faciliter heritage**/

    private double revenu_brut;

    private double paie_employes;

    private double revenu_net;


    private List<FactureDTO> factures;

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

    public List<FactureDTO> getFactures() {
        return factures;
    }

    public void setFactures(List<FactureDTO> factures) {
        this.factures = factures;
    }
}
