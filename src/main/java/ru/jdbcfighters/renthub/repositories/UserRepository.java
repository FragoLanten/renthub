package ru.jdbcfighters.renthub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jdbcfighters.renthub.domain.models.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByFirstNameOrLastNameOrLogin(String firstname, String lastname, String login);

    List<User> findUsersByFirstNameAndLastName(String firstname, String lastname);

    User findUsersByLogin(String login);

    List<User> findUsersByBalance(BigDecimal balance);

    List<User> findUsersByPhoneNumber(String phone);

    List<User> findUsersByDeletedNot(Boolean isDeleted);

}
