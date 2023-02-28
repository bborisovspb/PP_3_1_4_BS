package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


@Entity
@Table(name = "roles")
@Transactional
public class Role implements GrantedAuthority {

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "role_name")
    private String role;


    @ManyToMany(
            mappedBy = "roles")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    @Override
    public String getAuthority() {
        return role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int roleId) {
        this.Id = Id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }


}
