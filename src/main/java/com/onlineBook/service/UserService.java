package com.onlineBook.service;

import java.util.Optional;

import com.onlineBook.entity.User;

public interface UserService {
  void addUser(User user);
  Optional<User> getUserByEmail(String email);
}
