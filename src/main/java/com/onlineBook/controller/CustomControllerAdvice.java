package com.onlineBook.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.onlineBook.entity.User;
import com.onlineBook.service.security.CurrentUser;

@ControllerAdvice
public class CustomControllerAdvice {

  @ModelAttribute("currentUser")
  public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
    if (currentUser != null && currentUser.getUser() != null) return currentUser.getUser();
    return null;
  }

}
