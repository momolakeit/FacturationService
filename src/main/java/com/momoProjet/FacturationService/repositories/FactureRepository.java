package com.momoProjet.FacturationService.repositories;

import com.momoProjet.FacturationService.models.entities.Facture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository<T extends Facture> extends CrudRepository<T,Long>  {
}
