package com.projektseminarmicroservices.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "my_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Must give a name")
    private String username;

    @NotBlank(message = "Email can not be empty")
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Organization Form can not be empty")
    private String organizationForm;

    private Long departmentId;
}
