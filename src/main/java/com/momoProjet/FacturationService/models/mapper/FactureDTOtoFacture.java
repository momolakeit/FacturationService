package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.FactureDTO;
import com.momoProjet.FacturationService.models.entities.Facture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FactureDTOtoFacture extends MapperInterface<Facture,FactureDTO> {
    FactureDTOtoFacture instance = Mappers.getMapper(FactureDTOtoFacture.class);

    @Mapping(target = "comptes", ignore = true)
    @Mapping(target = "proprio", ignore = true)
    @Mapping(target = "factureAssocie", ignore = true)
    Facture convert(FactureDTO factureDTO);

}

