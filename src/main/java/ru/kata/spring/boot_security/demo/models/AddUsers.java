package ru.kata.spring.boot_security.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddUsers {

    private  final UserServiceImpl userService;


    @Autowired
    public AddUsers(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Transactional
    @Bean
    public void createUser() {

        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");

        User user1 = new User("Ivan", "Petrov", 35, "ivan@mail.com", "ivan",
                "$2a$12$v7DIffS2AH8mRKiTv1YRoexBcm8dxsM14Vuweu2t97wkvx7/LMI3u", new ArrayList<>(List.of(role1)));

        User user2 = new User("Oleg", "Ivanov", 36, "oleg@mail.com", "oleg",
                "$2a$12$QDZXGP2tiq.DRTYHm5hIWuc2fiMLI9cwtWJjVIWYDcSnif73ltNYW", new ArrayList<>(List.of(role1)));


        User user3 = new User("Pavel", "Borisov", 37, "pavel@mail.com", "pavel",
                "$2a$12$Wn73i.bDNuoG0wk1OWTo0uLh3mVYB4vbmXOygwQNy2K7Kip47EeM2", new ArrayList<>(List.of(role2)));

        User user4 = new User("Boris", "Pavlov", 38, "boris@mail.com", "boris",
                "$2a$12$CnFfcNLWTfXLViRXAGUL1O.Jh/8evOECCmSDi7aJ.fUPi0iotbYjC", new ArrayList<>(List.of(role2)));


        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
        userService.saveUser(user4);
    }
}
