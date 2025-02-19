package com.onlineBook.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineBook.entity.User;
import com.onlineBook.service.security.CurrentUser;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @GetMapping
  public String home(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
    if (currentUser != null && currentUser.getUser() != null) {
      User user = currentUser.getUser();
      modelMap.put("user", user);
    }
    return "admin/home";
  }
}
