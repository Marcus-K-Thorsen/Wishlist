package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  void saveUser(User user);

  void deleteUserById(String id);

  List<User> getAllUsers();

  Optional<User> getUserById(String id);

  boolean loginTest(String id, String password);
}

