package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/index";
    }

    @GetMapping("/new")
    public String getViewForCreateUsers(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/id")
    public String userInfo(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.userInfo(id));
        return "userInfo";
    }

    @GetMapping("/edit")
    public String getViewForEditUser(Principal principal, Model model, @RequestParam("edit") int id) {
        User loggedInUser = userService.findByUserName(principal.getName());
        List<Role> roleList = loggedInUser.getRoles();
        model.addAttribute("roleList", roleList);
        model.addAttribute("user", userService.userInfo(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("delete") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/BS")
    public String getUserName(Principal principal, Model model) {
        User loggedInUser = userService.findByUserName(principal.getName());
        List<Role> roleList = loggedInUser.getRoles();
        model.addAttribute("roleList", roleList);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userLoggedIn", loggedInUser);
        model.addAttribute("newUser", new User());
        model.addAttribute("users_roles", loggedInUser.getRoles());

        return "pipiskaadmin1";
    }

}
