package com.example.digitalwishlist.controller;

import com.example.digitalwishlist.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

  private final UserServiceImpl userService;

  public LoginController(UserServiceImpl userService) {
    this.userService = userService;
  }


  @PostMapping("/login-submit")
  public String login(WebRequest req, HttpSession session) {
    String username = req.getParameter("brugernavn");
    String password = req.getParameter("kodeord");

    boolean test = userService.credintialCheck(username, password);
    if (test) {
      session.setAttribute("usernameSession", username);
      return "redirect:/user_homepage";
    } else return "redirect:/";
  }
}
