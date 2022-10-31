package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/user/get")
  public List<User> getUser() {
    return userService.getUser();
  }

  @GetMapping(path = "/user/getAll")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping(path = "/user/post")
  public void registerNewUser(@RequestBody User user) {
    userService.save(user);
  }

  @DeleteMapping(path = "/user/delete/{userId}")
  public void deleteUser(@PathVariable("userId") String email) {
    userService.delete(email);
  }

  @PutMapping(path = "/user/put/password/{userId}")
  public void updateUserPassword(
      @PathVariable("userId") String email,
      @RequestParam(required = false) String password) {
    userService.updatePassword(email, password);
  }

  @PutMapping(path = "/user/put/givenName/{userId}")
  public void updateUserGivenName(
      @PathVariable("userId") String email,
      @RequestParam(required = false) String givenName) {
    userService.updateGivenName(email, givenName);
  }

  @PutMapping(path = "/user/put/lastName/{userId}")
  public void updateUserLastName(
      @PathVariable("userId") String email,
      @RequestParam(required = false) String lastName) {
    userService.updateLastName(email, lastName);
  }
}
