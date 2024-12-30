package com.onlineBook.service.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.onlineBook.entity.User;


public class CurrentUser extends org.springframework.security.core.userdetails.User{
  
  private transient User user;

  public CurrentUser(User user) {
    super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
    this.user = user;
  }

  public User getUser() {
    return user;
  }

}
