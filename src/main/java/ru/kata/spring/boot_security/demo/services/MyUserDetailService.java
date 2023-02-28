package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UsersRepository usersRepository;

    @Autowired
    public MyUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> users = usersRepository.findByUsername(s);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return users.get();
        }
    }
}
