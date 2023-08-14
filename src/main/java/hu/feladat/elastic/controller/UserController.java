package hu.feladat.elastic.controller;


import hu.feladat.elastic.dto.CreateUserRequest;
import hu.feladat.elastic.dto.UserResponse;
import hu.feladat.elastic.dto.UserSearchRequest;
import hu.feladat.elastic.dto.UserSearchResponse;
import hu.feladat.elastic.entity.User;
import hu.feladat.elastic.exception.ElasticException;
import hu.feladat.elastic.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elasticsearch")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request, BindingResult result) {
        processErrors(result);
        return  ResponseEntity.ok(service.create(request.getFirstName(), request.getLastName(), request.getEmail()));
    }


    @PostMapping("/search")
    public ResponseEntity<UserSearchResponse> search(@Valid @RequestBody UserSearchRequest request, BindingResult result) {
        processErrors(result);
        return ResponseEntity.ok(service.search(request.getFirstName(), request.getLastName()));
    }

    private void processErrors(BindingResult result) {
        if(result.hasErrors()) {
            throw new ElasticException(result.getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).toList());
        }
    }
}
