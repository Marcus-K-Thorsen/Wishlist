package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.repository.UserRepository;
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
  public void deleteUserById(String id) {
    boolean exists = userRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("User with email:  " + id + " does not exist");
    }
    this.userRepository.deleteById(id);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> getUserById(String id) {
    Optional<User> optional = userRepository.findById(id);
    User user;
    if (optional.isPresent()) {
      user = optional.get();
    } else {
      throw new RuntimeException(" User not found for email :: " + id);
    }
    return Optional.of(user);
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
