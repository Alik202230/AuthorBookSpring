package com.onlineBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineBook.entity.User;
import com.onlineBook.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
  
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String getUserPage() {
    return "register";
  }

  @PostMapping("/register")
  public String createUser(@ModelAttribute User user) {
    this.userService.addUser(user);
    return "redirect:/";
  }

}
