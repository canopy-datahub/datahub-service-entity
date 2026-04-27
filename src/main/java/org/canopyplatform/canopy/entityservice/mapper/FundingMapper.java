package org.canopyplatform.canopy.entityservice.mapper;

import org.canopyplatform.canopy.entityservice.model.DTO.FundingDTO;
import org.canopyplatform.canopy.entityservice.model.Funding;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FundingMapper {

    @Named("allFundings")
    FundingDTO fundingToAllFundingDTO(Funding funding);

    @IterableMapping(qualifiedByName = "allFundings")
    List<FundingDTO> fundingToAllFundingDTOs(List<Funding> fundings);
}
