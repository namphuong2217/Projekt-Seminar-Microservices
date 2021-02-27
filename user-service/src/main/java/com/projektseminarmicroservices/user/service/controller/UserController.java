package com.projektseminarmicroservices.user.service.controller;


import com.projektseminarmicroservices.user.service.DTO.ResponseDTO;
import com.projektseminarmicroservices.user.service.exception.RecordNotFoundException;
import com.projektseminarmicroservices.user.service.exception.ResourceAlreadyExistsException;
import com.projektseminarmicroservices.user.service.model.User;
import com.projektseminarmicroservices.user.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    // Task 1: Query UserService ID
    @GetMapping("/service-id")
    public ResponseEntity<String> getUserServiceId() {
        log.info("Inside register() method of getUserServiceId");
        return ResponseEntity.status(HttpStatus.OK).body(instanceId);
    }

    // Task 2: Add new user. If email is not unique, throw exception
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        log.info("Inside register() method of UserController");
        if(userService.emailExisted(user.getEmail()))
            throw new ResourceAlreadyExistsException("Email already registered");
        User newUser = userService.register(user);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    // Task 3: Query a user with id. If User Id does not exist, throw exception
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        log.info("Inside getUserById() method of UserController");
        User user = userService.findUserById(id);
        if (user == null)
            throw new RecordNotFoundException("User with given id not found: " + id);
        return user;
    }

    // Task 4 Query user with department name
    @GetMapping("/department/{id}")
    public ResponseDTO getUserWithDepartment(@PathVariable Long id){
        return userService.getUserWithDepartment(id);
    }
}
