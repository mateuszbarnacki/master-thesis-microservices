package agh.wfiis.weather.principal.service;

import agh.wfiis.weather.exception.UserAlreadyExistsException;
import agh.wfiis.weather.principal.dto.UserDto;
import agh.wfiis.weather.principal.model.UserEntity;
import agh.wfiis.weather.principal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
class UserRestServiceTest {
    private static final String TEST_USERNAME = "Tester";
    @Autowired
    private UserRestService userRestService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapper userMapper;

    @Test
    void shouldLoadUserByUsername() {
        UserEntity entity = new UserEntity();
        entity.setUsername(TEST_USERNAME);
        Mockito.when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(entity));

        String username = TEST_USERNAME;

        UserDetails user = whenLoadUserByUsername(username);

        thenUserContainsExpectedUsername(user);

        Mockito.verify(userRepository).findByUsername(ArgumentMatchers.anyString());
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionBecauseUserDoesNotExist() {
        Mockito.when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.empty());

        String username = TEST_USERNAME;

        thenMethodThrowsUsernameNotFoundException(username);

        Mockito.verify(userRepository).findByUsername(ArgumentMatchers.anyString());
    }

    @Test
    void shouldRegisterUser() {
        Mockito.when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.empty());
        Mockito.when(userMapper.mapDtoToEntity(ArgumentMatchers.any(UserDto.class))).thenReturn(new UserEntity());

        UserDto dto = givenUserDto();

        whenRegisterUser(dto);

        Mockito.verify(userRepository).save(ArgumentMatchers.any(UserEntity.class));
    }

    @Test
    void shouldNotRegisterUserAndThrowUserAlreadyExistsException() {
        Mockito.when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(new UserEntity()));

        UserDto dto = givenUserDto();

        thenMethodThrowsUserAlreadyExistsException(dto);

        Mockito.verify(userRepository).findByUsername(dto.username());
    }

    private UserDto givenUserDto() {
        return new UserDto(TEST_USERNAME,
                "tester@mail.com",
                "Test user",
                "1234",
                Set.of());
    }

    private UserDetails whenLoadUserByUsername(String username) {
        return userRestService.loadUserByUsername(username);
    }

    private void whenRegisterUser(UserDto dto) {
        userRestService.registerUser(dto);
    }

    private void thenUserContainsExpectedUsername(UserDetails userDetails) {
        Assertions.assertEquals(TEST_USERNAME, userDetails.getUsername());
    }

    private void thenMethodThrowsUsernameNotFoundException(String username) {
        Assertions.assertThrowsExactly(UsernameNotFoundException.class, () -> userRestService.loadUserByUsername(username));
    }

    private void thenMethodThrowsUserAlreadyExistsException(UserDto dto) {
        Assertions.assertThrowsExactly(UserAlreadyExistsException.class, () -> userRestService.registerUser(dto));
    }
}
