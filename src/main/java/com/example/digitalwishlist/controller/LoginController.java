package com.example.digitalwishlist.controller;

import com.example.digitalwishlist.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping
public class LoginController {

  private final UserServiceImpl userService;

  public LoginController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @PostMapping("/login-submit")
  public String login(WebRequest req) {
    if (userService.getUserById(req.getParameter("brugernavn")).get().getPassword().equals(req.getParameter("kodeord")))
      return "test";
    else return "redirect:/";
  }
}
