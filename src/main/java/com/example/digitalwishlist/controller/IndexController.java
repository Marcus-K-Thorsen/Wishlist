package com.example.digitalwishlist.controller;

import com.example.digitalwishlist.model.DTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping(value = {""})
public class IndexController {
  @GetMapping(value = {"/"})
  public String index() {
    return "index";
  }

    @GetMapping(value = {"/login"})
  public String login() {
    return "login";
  }

  @GetMapping(value = {"/signup"})
  public String createUser() {
    return "create_user";
  }

  @GetMapping(value = {"/signup-fail"})
  public String createUserFail(Model model) {
    model.addAttribute("message", "Det var ikke muligt at skabe en bruger med det input");
    return "create_user";
  }

  @GetMapping(value = {"/complete-fail"})
  public String fail(Model model) {
    DTO failData = (DTO) model.getAttribute("message");
    if (failData != null) {
      String failMessage = failData.getWishlistId();
      model.addAttribute("message", failMessage);
    }
    return "index";
  }

  /*  @GetMapping(value = {"/login"})
  public String login() {
    return "redirect:/user_homepage";
  }*/

/*  @GetMapping(value = {"/signup"})
  public String createUser() {
    return "create_user";
  }*/

}
