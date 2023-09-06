package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jdbcfighters.renthub.domain.dto.UserRequestDto;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.domain.models.enums.Role;
import ru.jdbcfighters.renthub.repositories.UserRepository;
import ru.jdbcfighters.renthub.services.UserService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        Optional<User> foundUser = userRepository.findById(id);

        return foundUser.orElseThrow(this::throwNotFoundException);
    }

    @Override
    public List<User> getByFirstOrLastOrLog(String firstname, String lastname, String login) {
        return userRepository.findUsersByFirstNameOrLastNameOrLogin(firstname, lastname, login);
    }

    @Override
    public List<User> getByFirstAndLast(String firstname, String lastname) {
        return userRepository.findUsersByFirstNameAndLastName(firstname, lastname);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findUsersByLogin(login);
    }

    @Override
    public List<User> getByPhone(String phone) {
        return userRepository.findUsersByPhoneNumber(phone);
    }

    @Override
    public List<User> getActiveUsers(Boolean active) {
        return userRepository.findUsersByDeletedNot(active);
    }

    @Override
    public List<User> getByBalance(BigDecimal balance) {
        return userRepository.findUsersByBalance(balance);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User update(Long id, User updatedUser) {
        existCheck(id);
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public User delete(Long id) {
        User deletedUser = get(id);
        deletedUser.setDeleted(true);
        return userRepository.save(deletedUser);
    }

    @Override
    @Transactional
    public void hardDelete(Long id) {
        existCheck(id);
        userRepository.deleteById(id);
    }

    private EntityNotFoundException throwNotFoundException() {
        return new EntityNotFoundException("Пользователь не найден");
    }

    private void existCheck(Long id) {
        if (!userRepository.existsById(id))
            throw throwNotFoundException();
    }

    @Override
    @Transactional
    public void updateUser(String login, UserRequestDto userRequestDto) {

    }

    @Override
    @Transactional
    public void banned(long id) {
        Optional<User> updatedUser = userRepository.findById(id);
        if (updatedUser.isEmpty()) {
            throw new UsernameNotFoundException("User with id " + id + " not found");
        }
        updatedUser.get().setDeleted(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> searchResult = userRepository.findByLogin(username);
            if (searchResult.isPresent()) {
                User user = searchResult.get();
                String login = user.getLogin();
                String password = user.getPassword();
                return new org.springframework.security.core.userdetails.User(
                        login,
                        password,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                user.getAuthorities()
                                        .stream()
                                        .map(Role::name)
                                        .collect(Collectors.joining(","))
                        )
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this username not found");
        }
    }
}
