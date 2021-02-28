package com.projektseminarmicroservices.user.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/users")
public class ServiceFactoryController {

    @GetMapping("/create-service")
    public void createNewService() throws IOException {
        String path = "/home/phuong/Desktop/BSc/Mikroservices/user-service/src/main/resources/user-service.jar";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        try {
//            ProcessBuilder pb = new ProcessBuilder("java", "-jar", Resources.getResource("user-service.jar").getPath());
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", absolutePath);
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
