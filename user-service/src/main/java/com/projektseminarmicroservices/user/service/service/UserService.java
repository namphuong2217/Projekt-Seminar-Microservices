package com.projektseminarmicroservices.user.service.service;

import com.projektseminarmicroservices.user.service.DTO.Department;
import com.projektseminarmicroservices.user.service.DTO.ResponseDTO;
import com.projektseminarmicroservices.user.service.model.User;
import com.projektseminarmicroservices.user.service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Task 1 handle persistence new user to database
    @Transactional(rollbackFor = Exception.class)
    public User register(User user) {
        log.info("Inside register() method of UserService");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setOrganizationForm(user.getOrganizationForm());
        newUser.setDepartmentId(user.getDepartmentId());
        return userRepository.save(user);
    }

    // Task 2 helper function. Find user by email
    @Transactional(readOnly = true)
    public boolean emailExisted(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return true;
        else
            return false;
    }

    // Task 2 find user by id
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        log.info("Inside findUserById() method of UserService");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        else
            return null;
    }

    public ResponseDTO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment() method of UserService");
        ResponseDTO responseDTO = new ResponseDTO();
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
            return null;

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.get().getDepartmentId(),
                Department.class);

        responseDTO.setUser(user.get());
        responseDTO.setDepartment(department);

        return responseDTO;

    }

}
