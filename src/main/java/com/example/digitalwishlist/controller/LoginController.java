package com.example.digitalwishlist.controller;

import com.example.digitalwishlist.model.HTMLPreparer;
import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.service.UserServiceImpl;
import com.example.digitalwishlist.service.WishlistServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class LoginController {

  private final UserServiceImpl userService;
  private final WishlistServiceImpl wishlistService;

  public LoginController(UserServiceImpl userService, WishlistServiceImpl wishlistService) {
    this.userService = userService;
    this.wishlistService = wishlistService;
  }

  @PostMapping("/login-submit")
  public String login(Model model, WebRequest req) {
    String brugernavn = req.getParameter("brugernavn");
    String password = req.getParameter("kodeord");

    boolean test = userService.loginTest(brugernavn, password);
    if (test) {
      Optional<User> optionalUser = userService.getUserById(brugernavn);
      User user = optionalUser.get();
      List<Wishlist> userWishlists = wishlistService.getWishlistsByUserId(user);
      user.setWishlists(userWishlists);
      String HTMLListCode = HTMLPreparer.toDisplay4WishLists(user);
      model.addAttribute("HTMLCode", HTMLListCode);
      model.addAttribute("user",user);
      /*model.addAttribute("wishlists",userWishlists);*/
      return "view-user";
    } else return "redirect:/";
  }
}
