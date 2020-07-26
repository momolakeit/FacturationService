package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.ComptesDTO;
import com.momoProjet.FacturationService.models.entities.Comptes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComptesDTOToComptes extends MapperInterface <Comptes,ComptesDTO>{
    ComptesDTOToComptes instance = Mappers.getMapper(ComptesDTOToComptes.class);
    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "proprioFacture", ignore = true)
    Comptes convert(ComptesDTO comptes);

}
