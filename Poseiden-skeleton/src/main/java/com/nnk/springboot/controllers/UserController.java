package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controller responsible for handling User CRUD operations.
 */
@Controller
public class UserController {

    private final UserService userService;

    /**
     * Constructor-based dependency injection of UserService.
     *
     * @param userService service handling User business logic
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Display list of users.
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    /**
     * Display form to add a new user.
     */
    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    /**
     * Validate and save a new user.
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/add";
        }

        userService.saveUser(user);
        return "redirect:/user/list";
    }

    /**
     * Display form to update an existing user.
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findUserById(id);
        user.setPassword(""); // hide hashed password
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Update an existing user.
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id,
                             @Valid User user,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "user/update";
        }

        user.setId(id);
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    /**
     * Delete a user by id.
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
}