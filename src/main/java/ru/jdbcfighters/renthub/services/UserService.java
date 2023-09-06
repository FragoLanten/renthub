package ru.jdbcfighters.renthub.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.jdbcfighters.renthub.domain.dto.UserRequestDto;
import ru.jdbcfighters.renthub.domain.models.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService extends UserDetailsService {
    User save(User user);

    User get(Long id);

    List<User> getByFirstOrLastOrLog(String firstname, String lastname, String login);

    List<User> getByFirstAndLast(String firstname, String lastname);

    User getByLogin(String login);

    List<User> getByPhone(String phone);

    List<User> getActiveUsers(Boolean active);

    List<User> getByBalance(BigDecimal balance);

    List<User> getAll();

    User update(Long id, User user);

    //Is_deleted = true
    User delete(Long id);

    //Удаление из БД
    void hardDelete(Long id);

    void updateUser(String login, UserRequestDto userRequestDto);

    void banned(long id);
}
