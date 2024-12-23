package com.onlineBook.service.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlineBook.entity.User;
import com.onlineBook.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userByEmail = this.userRepository.findByEmail(username);
    if (userByEmail.isPresent()) {
      User userFromDb = userByEmail.get();
      return new CurrentUser(userFromDb);
    }
    throw new UsernameNotFoundException("User with " + username + " does not exist");
  }
  
}
