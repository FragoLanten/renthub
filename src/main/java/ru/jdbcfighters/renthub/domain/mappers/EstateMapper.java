package ru.jdbcfighters.renthub.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.models.Estate;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstateMapper {
    @Mappings({
            @Mapping(target = "city", ignore = true),
            @Mapping(target = "street", ignore = true)
    })
    Estate estateRequestDTOToEstate(EstateRequestDTO estateRequestDTO);

}
