package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.ComptesDTO;
import com.momoProjet.FacturationService.models.DTO.ProprioFactureDTO;
import com.momoProjet.FacturationService.models.entities.Comptes;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProprioFactureToProprioFactureDTO extends MapperInterface<ProprioFactureDTO,ProprioFacture> {
    ProprioFactureToProprioFactureDTO instance = Mappers.getMapper(ProprioFactureToProprioFactureDTO.class);
    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    ProprioFactureDTO convert(ProprioFacture proprioFacture);

}
