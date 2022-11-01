package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping
public class UserController {

  private final UserServiceImpl userService;

  @Autowired
  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  // display list of users
/*  @GetMapping("/")
  public String viewHomePage(Model model) {
    model.addAttribute("listUsers", userService.getAllUsers());
    return "index";
  }*/

  @GetMapping("/showNewUserForm")
  public String showNewUserForm(Model model) {
    // create model attribute to bind form data
    User user = new User();
    model.addAttribute("user", user);
    return "new_user";
  }

  @PostMapping("/saveUser")
  public String saveUser(WebRequest req) {
    if (Objects.equals(req.getParameter("kodeord1"), req.getParameter("kodeord2"))) {
      // save user to database
      User user = new User(req.getParameter("nytbrugernavn"), req.getParameter("kodeord1"), req.getParameter("fornavn"), req.getParameter("efternavn"));
      userService.saveUser(user);
      return "redirect:/";
    } else return "redirect:/signup";
  }

  //TODO: brug thymeleaf i stedet for WebRequest
/*  @PostMapping("/saveUser")
  public String saveUser(@ModelAttribute("user") User user) {
    // save user to database
    userService.saveUser(user);
    return "redirect:/";
  }*/

  @GetMapping("/showFormForUpdate/{userId}")
  public String showFormForUpdate(@PathVariable(value = "userId") String id, Model model) {

    // get user from the service
    Optional<User> user = userService.getUserById(id);

    // set user as a model attribute to pre-populate the form
    model.addAttribute("user", user);
    return "update_user";
  }

  @GetMapping("/deleteUser/{userId}")
  public String deleteUser(@PathVariable(value = "userId") String id) {

    // call delete user method
    this.userService.deleteUserById(id);
    return "redirect:/";
  }

/*  @PutMapping(path = "/put/password/{userId}")
  public void updateUserPassword(
      @PathVariable("userId") String email,
      @RequestParam(required = false) String password) {
    userService.updatePassword(email, password);
  }

  @PutMapping(path = "/put/givenName/{userId}")
  public void updateUserGivenName(
      @PathVariable("userId") String email,
      @RequestParam(required = false) String givenName) {
    userService.updateGivenName(email, givenName);
  }

  @PutMapping(path = "/put/lastName/{userId}")
  public void updateUserLastName(
      @PathVariable("userId") String email,
      @RequestParam(required = false) String lastName) {
    userService.updateLastName(email, lastName);
  }*/
}
