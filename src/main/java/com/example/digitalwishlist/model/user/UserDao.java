package com.example.digitalwishlist.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {

  private static UserRepository repository = null;

  @Autowired
  public UserDao(UserRepository repository) {
    UserDao.repository = repository;
  }

  public static void save(User user) {
    repository.save(user);
  }

  public void delete(String email) {
    boolean exists = repository.existsById(email);
    if (!exists) {
      throw new IllegalStateException("User with email:  " + email + " does not exist");
    }
    repository.deleteById(email);
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    Streamable.of(repository.findAll())
        .forEach(users::add);
    return users;
  }

  public List<User> getUser() {
    return (List<User>) repository.findAll();
  }

  @Transactional
  public void updatePassword(String email, String password) {
    User user = repository.findById(email).orElseThrow(() -> new IllegalStateException(
        "User with email:  " + email + " does not exist"));

    if (password != null && !password.equals(user.getPassword())) {
      user.setPassword(password);
    }
  }

  @Transactional
  public void updateFirstName(String email, String givenName) {
    User user = repository.findById(email).orElseThrow(() -> new IllegalStateException(
        "User with email:  " + email + " does not exist"));

    if (givenName != null && !givenName.equals(user.getGivenName())) {
      user.setGivenName(givenName);
    }
  }

  @Transactional
  public void updateLastName(String email, String lastName) {
    User user = repository.findById(email).orElseThrow(() -> new IllegalStateException(
        "User with email:  " + email + " does not exist"));

    if (lastName != null && !lastName.equals(user.getLastName())) {
      user.setLastName(lastName);
    }
  }
}
