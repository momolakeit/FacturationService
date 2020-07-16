package com.momoProjet.FacturationService.repositories;

import com.momoProjet.FacturationService.models.entities.Comptes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComptesRepository extends CrudRepository<Comptes,Long> {

}
