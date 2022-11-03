package com.example.digitalwishlist.model.service;

import com.example.digitalwishlist.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  void saveUser(User user);

  void deleteUserById(String email);

  List<User> getAllUsers();

  Optional<User> getUserById(String email);

  boolean credintialCheck(String email, String password);
}
