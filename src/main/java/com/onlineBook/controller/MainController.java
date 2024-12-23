package com.onlineBook.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.onlineBook.entity.Role;
import com.onlineBook.entity.User;
import com.onlineBook.service.security.CurrentUser;

@Controller
public class MainController {

  @GetMapping("/loginPage")
  public String loginPage(@AuthenticationPrincipal CurrentUser currentUser) {
    if (currentUser != null && currentUser.getUser() != null) return "redirect:/";
    return "login";
  }

  @GetMapping("/loginSuccess")
  public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
    if (currentUser != null && currentUser.getUser() != null) {
      User user = currentUser.getUser();
      if (user.getRole() == Role.ADMIN) {
        return "redirect:/admin";
      }
    }
    return "redirect:/";
  }

  //@GetMapping("/")
  // @RequestMapping(value = "/", method = RequestMethod.GET)
  // public String mainPage(ModelMap modelMap) {
  //   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  //   Object principal = authentication.getPrincipal();
  //   if (principal instanceof CurrentUser currentUser) {
  //     User user = currentUser.getUser();
  //     modelMap.put("user", user);
  //   }
  //   return "index";
  // }
  
  @GetMapping("/")
  public String mainPage() {
    return "index";
  } 

}
