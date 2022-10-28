package com.example.digitalwishlist.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {
  @Autowired
  private UserRepository repository;

  public void save(User user) {
    repository.save(user);
  }

  public void delete(User user) {
    repository.delete(user);
  }

  public void delete(String email) {
    repository.deleteById(email);
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    Streamable.of(repository.findAll())
        .forEach(users::add);
    return users;
  }
}