package com.projektseminarmicroservices.user.service.DTO;

import com.projektseminarmicroservices.user.service.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private User user;

    private Department department;
}
