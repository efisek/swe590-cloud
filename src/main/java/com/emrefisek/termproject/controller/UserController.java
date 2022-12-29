package com.emrefisek.termproject.controller;

import com.emrefisek.termproject.exception.ResourceNotFoundException;
import com.emrefisek.termproject.model.User;
import com.emrefisek.termproject.repository.UserRepository;
import com.emrefisek.termproject.security.CurrentUser;
import com.emrefisek.termproject.security.UserPrincipal;
import com.emrefisek.termproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getByUserId(userPrincipal.getId());
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public String deleteCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        userService.deleteById(userPrincipal.getId());
        return "User with " + userPrincipal.getId() + " successfully deleted";
    }

    @PostMapping("/follow")
    @PreAuthorize("hasRole('USER')")
    public String follow(@CurrentUser UserPrincipal userPrincipal, String userId) {
        userService.followById(userPrincipal.getId(), userId);

        return "User with " + userPrincipal.getId() + " successfully deleted";
    }

}
