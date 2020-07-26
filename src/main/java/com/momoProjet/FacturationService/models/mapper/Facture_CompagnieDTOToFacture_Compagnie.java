package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.Facture_CompagnieDTO;
import com.momoProjet.FacturationService.models.entities.Facture_Compagnie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Facture_CompagnieDTOToFacture_Compagnie extends MapperInterface <Facture_Compagnie,Facture_CompagnieDTO> {

    Facture_CompagnieDTOToFacture_Compagnie instance = Mappers.getMapper(Facture_CompagnieDTOToFacture_Compagnie.class);


    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    @Mapping(target = "proprio", ignore = true)
    Facture_Compagnie convert(Facture_CompagnieDTO facture_compagnie);

}

