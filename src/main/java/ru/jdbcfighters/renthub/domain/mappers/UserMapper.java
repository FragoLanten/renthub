package ru.jdbcfighters.renthub.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.jdbcfighters.renthub.domain.dto.RegistrationUserRequestDto;
import ru.jdbcfighters.renthub.domain.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "revenues", ignore = true)
    @Mapping(target = "wishlist", ignore = true)
    @Mapping(target = "estateList", ignore = true)
    @Mapping(target = "role", ignore = true)
    User userRegistrationDtoToUser(RegistrationUserRequestDto registrationUserRequestDto);

}
