package com.example.digitalwishlist.model.service;

import com.example.digitalwishlist.model.entity.User;
import com.example.digitalwishlist.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void saveUser(User user) {
    this.userRepository.save(user);
  }

  @Override
  public void deleteUserById(String email) {
    boolean exists = userRepository.existsById(email);
    if (!exists) {
      throw new IllegalStateException("User with email:  " + email + " does not exist");
    }
    this.userRepository.deleteById(email);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> getUserById(String email) {
    Optional<User> optional = userRepository.findById(email);
    User user;
    if (optional.isPresent()) {
      user = optional.get();
    } else {
      throw new RuntimeException(" User not found for email :: " + email);
    }
    return Optional.of(user);
  }

  @Override
  public boolean credintialCheck(String email, String password) {
    try {
      Optional<User> user = getUserById(email);
      return password.equals(user.get().getPassword());

    } catch (Exception e) {
      System.out.println("FEJL!");
    }
    return false;
  }


/*  @Transactional
  public void updatePassword(String id, String password) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
        "User with email:  " + id + " does not exist"));

    if (password != null && !password.equals(user.getPassword())) {
      user.setPassword(password);
    }
  }

  @Transactional
  public void updateGivenName(String id, String givenName) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
        "User with email:  " + id + " does not exist"));

    if (givenName != null && !givenName.equals(user.getGivenName())) {
      user.setGivenName(givenName);
    }
  }

  @Transactional
  public void updateLastName(String id, String lastName) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
        "User with email:  " + id + " does not exist"));

    if (lastName != null && !lastName.equals(user.getLastName())) {
      user.setLastName(lastName);
    }
  }*/
}
