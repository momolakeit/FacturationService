package com.momoProjet.FacturationService.repositories;

import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprioFactureRepository extends CrudRepository<ProprioFacture,Long> {
    public ProprioFacture findByEmail(String email);
}
