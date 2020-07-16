package com.momoProjet.FacturationService.models.mapper;

import com.momoProjet.FacturationService.models.DTO.FactureDTO;
import com.momoProjet.FacturationService.models.DTO.Facture_CompagnieDTO;
import com.momoProjet.FacturationService.models.entities.Facture;
import com.momoProjet.FacturationService.models.entities.Facture_Compagnie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Facture_CompagnieToFacture_CompagnieDTO extends MapperInterface <Facture_CompagnieDTO,Facture_Compagnie> {

    Facture_CompagnieToFacture_CompagnieDTO instance = Mappers.getMapper(Facture_CompagnieToFacture_CompagnieDTO.class);


    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    @Mapping(target = "proprio", ignore = true)
    Facture_CompagnieDTO convert(Facture_Compagnie facture_compagnie);

}

