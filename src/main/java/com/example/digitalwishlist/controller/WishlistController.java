package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.service.UserServiceImpl;
import com.example.digitalwishlist.service.WishlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping
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
  public String viewHomePage(Model model) {
    model.addAttribute("listWishlists", wishlistService.getAllWishlists());
    return "/test/view_user";
  }

  @GetMapping("/showNewWishlistForm")
  public String showNewWishlistForm(Model model) {
    // create model attribute to bind form data
    Wishlist wishlist = new Wishlist();
    model.addAttribute("wishlist", wishlist);
    return "/test/new_wishlist";
  }

  @PostMapping("/saveWishlist")
  public String saveWishlist(@ModelAttribute("wishlist") Wishlist wishlist) {
    // save wishlist to database
    wishlistService.saveWishlist(wishlist);
    return "redirect:/";
  }

  @GetMapping("/showFormForUpdate/{id}")
  public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

    // get wishlist from the service
    Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);

    // set wishlist as a model attribute to pre-populate the form
    model.addAttribute("wishlist", wishlist);
    return "/test/update_wishlist";
  }

  @GetMapping("/deleteWishlist/{id}")
  public String deleteWishlist(@PathVariable(value = "id") long id) {

    // call delete wishlist method
    this.wishlistService.deleteWishlistById(id);
    return "redirect:/";
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
