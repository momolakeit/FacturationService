package com.momoProjet.FacturationService.models.DTO;

import com.momoProjet.FacturationService.models.entities.Comptes;
import com.momoProjet.FacturationService.models.entities.Facture;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class ProprioFactureDTO {
    private long id;

    private String email;


    private List<FactureDTO> factures;


    private ComptesDTO comptes;

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

    public List<FactureDTO> getFactures() {
        return factures;
    }

    public void setFactures(List<FactureDTO> factures) {
        this.factures = factures;
    }

    public ComptesDTO getComptes() {
        return comptes;
    }

    public void setComptes(ComptesDTO comptes) {
        this.comptes = comptes;
    }
}
