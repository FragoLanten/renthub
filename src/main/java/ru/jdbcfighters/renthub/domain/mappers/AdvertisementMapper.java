package ru.jdbcfighters.renthub.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.models.Advertisement;

import java.time.LocalDate;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = {LocalDate.class})
public interface AdvertisementMapper {

    @Mapping(target = "endDate", expression = "java(LocalDate.now())")
    @Mapping(target = "visible", expression = "java(true)")
    @Mapping(target = "rank", expression = "java(1)")
    @Mapping(target = "moderated", expression = "java(false)")
    Advertisement estateRequestDTOToAdvertisement(EstateRequestDTO estateRequestDTO);
}
