package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.user.User;
import com.example.digitalwishlist.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

  private final UserDao userDao;

  @Autowired
  public UserController(UserDao userDao) {
    this.userDao = userDao;
  }

  @GetMapping(path = "/user/get")
  public List<User> getUser() {
    return userDao.getUser();
  }

  @GetMapping(path = "/user/getAll")
  public List<User> getAllUsers() {
    return userDao.getAllUsers();
  }

  @PostMapping(path = "/user/post")
  public void registerNewUser(@RequestBody User user) {
    UserDao.save(user);
  }

  @DeleteMapping(path = "/user/delete/{userId}")
  public void deleteUser(@PathVariable("userId") String email) {
    userDao.delete(email);
  }

  @PutMapping(path = "/user/put/{userId}")
  public void updateStudent(
      @PathVariable("userId") String email,
      @RequestParam(required = false) String password) {
    userDao.updatePassword(email, password);
  }
}
