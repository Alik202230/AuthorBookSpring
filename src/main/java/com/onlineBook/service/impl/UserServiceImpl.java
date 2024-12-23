package com.onlineBook.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineBook.entity.User;
import com.onlineBook.repository.UserRepository;
import com.onlineBook.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    this.userRepository.save(user);
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  
}
