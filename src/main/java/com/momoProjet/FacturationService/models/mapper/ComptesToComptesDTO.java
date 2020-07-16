package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.ComptesDTO;
import com.momoProjet.FacturationService.models.entities.Comptes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComptesToComptesDTO extends MapperInterface <ComptesDTO,Comptes>{
    ComptesToComptesDTO instance = Mappers.getMapper(ComptesToComptesDTO.class);
    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "proprioFacture", ignore = true)
    ComptesDTO convert(Comptes comptes);

}
