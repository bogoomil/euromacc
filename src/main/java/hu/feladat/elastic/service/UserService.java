package hu.feladat.elastic.service;

import hu.feladat.elastic.dto.UserResponse;
import hu.feladat.elastic.dto.UserSearchResponse;
import hu.feladat.elastic.entity.User;
import hu.feladat.elastic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository repository;

    public UserService(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse create(String firstName, String lastName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return convertEntityToDto(repository.save(user));
    }

    public UserSearchResponse search(String firstName, String lastName) {
        List<User> users = repository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
        UserSearchResponse response = convertSearchResultToDto(users);
        return response;
    }

    private UserSearchResponse convertSearchResultToDto(List<User> users) {
        UserSearchResponse response = new UserSearchResponse();
        response.setTotal((long) users.size());
        users.forEach(user -> response.getUsers().add(convertEntityToDto(user)));
        return response;
    }

    private UserResponse convertEntityToDto(User user) {
        UserResponse response = new UserResponse();
        response.setUuid(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        return response;
    }
}
