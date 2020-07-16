package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.ComptesDTO;
import com.momoProjet.FacturationService.models.DTO.FactureDTO;
import com.momoProjet.FacturationService.models.entities.Comptes;
import com.momoProjet.FacturationService.models.entities.Facture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FactureToFactureDTO extends MapperInterface<FactureDTO,Facture> {
    FactureToFactureDTO instance = Mappers.getMapper(FactureToFactureDTO.class);

    @Mapping(target = "comptes", ignore = true)
    @Mapping(target = "proprio", ignore = true)
    @Mapping(target = "facture_compagnie", ignore = true)
    FactureDTO convert(Facture facture);

}

