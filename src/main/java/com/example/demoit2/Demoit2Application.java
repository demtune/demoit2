package com.example.demoit2;

import com.example.demoit2.entity.collection.CollectionEntity;
import com.example.demoit2.entity.collection.TopicEntity;
import com.example.demoit2.entity.items.ItemEntity;
import com.example.demoit2.entity.users.RoleEntity;
import com.example.demoit2.entity.users.UserEntity;
import com.example.demoit2.service.CollectionService;
import com.example.demoit2.service.ItemService;
import com.example.demoit2.service.TopicService;
import com.example.demoit2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@SpringBootApplication
public class Demoit2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demoit2Application.class, args);
    }


    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            userService.saveRole(new RoleEntity(null, "ROLE_USER"));
            userService.saveRole(new RoleEntity(null, "ROLE_MANAGER"));
            userService.saveRole(new RoleEntity(null, "ROLE_ADMIN"));


            userService.saveUser(new UserEntity(null, "john", bCryptPasswordEncoder.encode("12345"), "john_travolta@gmail.com", LocalDateTime.now(), LocalDateTime.now(), true, new HashSet<>()));
            userService.saveUser(new UserEntity(null, "will", bCryptPasswordEncoder.encode("2321314"), "will_smith@gmail.com", LocalDateTime.now(), LocalDateTime.now(), true, new HashSet<>()));
            userService.saveUser(new UserEntity(null, "jim", bCryptPasswordEncoder.encode("1"), "jim_carry@gmail.com", LocalDateTime.now(), LocalDateTime.now(), true, new HashSet<>()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_USER");
            userService.addRoleToUser("jim", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_ADMIN");
        };
    }
}
