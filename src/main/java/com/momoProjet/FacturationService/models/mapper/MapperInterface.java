package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.ProprioFactureDTO;
import com.momoProjet.FacturationService.models.entities.ProprioFacture;
import org.mapstruct.factory.Mappers;

public interface  MapperInterface <T, Q> {
    MapperInterface instance = Mappers.getMapper(MapperInterface.class);
    T convert(Q objToConvert);

}
