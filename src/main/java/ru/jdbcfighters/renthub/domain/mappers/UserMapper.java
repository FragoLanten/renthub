package ru.jdbcfighters.renthub.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.jdbcfighters.renthub.domain.dto.RegistrationUserRequestDto;
import ru.jdbcfighters.renthub.domain.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "wallets", expression = "java(walletsToWalletsResponse(wallets))")
//    @Mapping(target = "accounts", expression = "java(accountToAccountsResponse(accounts))")
//    UserResponseDto userToUserResponseDto(User user, List<Wallet> wallets, List<Account> accounts);

//    default User userRequestToUser(UserRequestDto userRequestDto, User userFromBD) {
//        userFromBD.setUsername(userRequestDto.username());
//        userFromBD.setEmail(userRequestDto.email());
//        userFromBD.setFirstName(userRequestDto.firstName());
//        userFromBD.setLastName(userRequestDto.lastName());
//        userFromBD.setPhone(userRequestDto.phone());
//        userFromBD.setBirthDate(userRequestDto.birthDate());
//        userFromBD.setUpdateTime(LocalDateTime.now());
//
//        return userFromBD;
//    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "revenues", ignore = true)
    @Mapping(target = "wishlist", ignore = true)
    @Mapping(target = "estateList", ignore = true)
    @Mapping(target = "role", ignore = true)
    User userRegistrationDtoToUser(RegistrationUserRequestDto registrationUserRequestDto);

//    UserDto userToUserDto(User user);
}
