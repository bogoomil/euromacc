package hu.feladat.elastic.service;

import hu.feladat.elastic.dto.UserResponse;
import hu.feladat.elastic.dto.UserSearchResponse;
import hu.feladat.elastic.entity.User;
import hu.feladat.elastic.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;
    private User user;
    private List<User> users;
    private EasyRandom easyRandom;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        easyRandom = new EasyRandom();

        user = easyRandom.nextObject(User.class);
        users = easyRandom.objects(User.class, 5).collect(Collectors.toList());

        Mockito.when(userRepository.findByFirstNameLikeOrLastNameLike("John", "Doe")).thenReturn(users);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        userService = new UserService(userRepository);
    }

    @Test
    void create() {
        UserResponse response = userService.create(user.getFirstName(), user.getLastName(), user.getEmail());
        assertEquals(user.getFirstName(), response.getFirstName());
        assertEquals(user.getLastName(), response.getLastName());
        assertEquals(user.getEmail(), response.getEmail());
        assertNotNull(response.getUuid());
    }

    @Test
    void search() {
        UserSearchResponse response = userService.search("John", "Doe");
        assertEquals(response.getTotal(), (long) users.size());
        assertEquals(response.getUsers().get(0).getFirstName(), users.get(0).getFirstName());
        assertEquals(response.getUsers().get(0).getLastName(), users.get(0).getLastName());
        assertEquals(response.getUsers().get(0).getEmail(), users.get(0).getEmail());
        assertNotNull(response.getUsers().get(0).getUuid());
    }
}