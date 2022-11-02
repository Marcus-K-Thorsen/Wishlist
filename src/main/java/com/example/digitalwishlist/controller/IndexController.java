package com.example.digitalwishlist.controller;

import com.example.digitalwishlist.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {""})
public class IndexController {
  @GetMapping(value = {"/"})
  public String index() {
    return "test/index";
  }

/*  @GetMapping(value = {"/login"})
  public String login() {
    return "redirect:/user_homepage";
  }*/

    @GetMapping(value = {"/login"})
  public String login() {
    return "test/login";
  }

  @GetMapping(value = {"/signup"})
  public String createUser() {
    return "redirect:/showNewUserForm";
  }

/*  @GetMapping(value = {"/signup"})
  public String createUser() {
    return "create_user";
  }*/

}
