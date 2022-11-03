package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.entity.Wishlist;
import com.example.digitalwishlist.model.service.UserServiceImpl;
import com.example.digitalwishlist.model.service.WishlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WishlistController {

  private final WishlistServiceImpl wishlistService;
  private final UserServiceImpl userService;

  @Autowired
  public WishlistController(WishlistServiceImpl wishlistService, UserServiceImpl userService) {
    this.wishlistService = wishlistService;
    this.userService = userService;
  }

  // display list of wishlists
  @GetMapping("/user_homepage")
  public String viewHomePage( Model model, HttpSession session) {
    String username = (String) session.getAttribute("usernameSession");
    List<Wishlist> sortedWishlist = wishlistService.getAllWishlistsByUser( username);
    model.addAttribute("listWishlists", sortedWishlist);
    return "test/user_homepage";
  }

  @GetMapping("/showNewWishlistForm")
  public String showNewWishlistForm(Model model) {
    // create model attribute to bind form data
    Wishlist wishlist = new Wishlist();
    model.addAttribute("wishlist", wishlist);
    return "test/new_wishlist";
  }

  @PostMapping("/saveWishlist")
  public String saveWishlist(@ModelAttribute("wishlist") Wishlist wishlist, HttpSession session) {
    String username = (String) session.getAttribute("usernameSession");

    // save wishlist to database
    wishlist.setUser(userService.getUserById(username).get());
    wishlistService.saveWishlist(wishlist);
    return "redirect:/user_homepage";
  }

  @PostMapping("/saveWishlistAfterUpdate")
  public String saveWishlistAfterUpdate(@ModelAttribute("wishlist") Wishlist wishlist, HttpSession session) {
    String username = (String) session.getAttribute("usernameSession");

    // save wishlist to database
    wishlist.setUser(userService.getUserById(username).get());
    wishlistService.saveWishlist(wishlist);

    // delete old wishlist from database
    long id = (long) session.getAttribute("WishlistIdSession");
    this.wishlistService.deleteWishlistById(id);
    return "redirect:/user_homepage";
  }

  @GetMapping("/showWishlistFormForUpdate/{id}")
  public String showWishlistFormForUpdate(@PathVariable(value = "id") long id, Model model, HttpSession session) {
    session.setAttribute("WishlistIdSession", id);
    // get wishlist from the service
    Wishlist wishlist = wishlistService.getWishlistById(id).get();

    // set wishlist as a model attribute to pre-populate the form
    model.addAttribute("wishlist", wishlist);
    return "test/update_wishlist";
  }

  @GetMapping("/deleteWishlist/{id}")
  public String deleteWishlist(@PathVariable(value = "id") long id) {

    // call delete wishlist method
    this.wishlistService.deleteWishlistById(id);
    return "redirect:/user_homepage";
  }

/*  @PutMapping(path = "/put/title/{wishlistId}")
  public void updateWishlistTitle(
      @PathVariable("wishlistId") Integer id,
      @RequestParam(required = false) String title) {
    wishlistService.updateTitle(id, title);
  }

  @PutMapping(path = "/put/descr/{wishlistId}")
  public void updateWishlistDescr(
      @PathVariable("wishlistId") Integer id,
      @RequestParam(required = false) String descr) {
    wishlistService.updateDescription(id, descr);
  }*/
}
