package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.ProprioFactureDTO;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProprioFactureDTOToProprioFacture extends MapperInterface<ProprioFacture,ProprioFactureDTO> {
    ProprioFactureDTOToProprioFacture instance = Mappers.getMapper(ProprioFactureDTOToProprioFacture.class);
    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    ProprioFacture convert(ProprioFactureDTO proprioFacture);

}
