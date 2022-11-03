package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.entity.Wish;
import com.example.digitalwishlist.model.service.WishServiceImpl;
import com.example.digitalwishlist.model.service.WishlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WishController {

  private final WishServiceImpl wishService;
  private final WishlistServiceImpl wishlistService;

  @Autowired
  public WishController(WishServiceImpl wishService, WishlistServiceImpl wishlistService) {
    this.wishService = wishService;
    this.wishlistService = wishlistService;
  }

  // display list of wishes
  @GetMapping("/wishlist_homepage/{id}")
  public String viewHomePage(@PathVariable(value = "id") long id, Model model, HttpSession session) {
    session.setAttribute("WLIdSession", id);
    List<Wish> sortedWishes = wishService.getAllWishesByWishlist( id);
    model.addAttribute("listWishes", sortedWishes);
    return "test/wishlist_homepage"; //"test/wishlist_homepage"
  }

  @GetMapping("/wishlist_homepage")
  public String viewHomePage(Model model, HttpSession session) {
    long id = (long) session.getAttribute("WLIdSession");
    List<Wish> sortedWishes = wishService.getAllWishesByWishlist( id);
    model.addAttribute("listWishes", sortedWishes);
    return "test/wishlist_homepage";
  }

  @GetMapping("/showNewWishForm")
  public String showNewWishForm(Model model) {
    // create model attribute to bind form data
    Wish wish = new Wish();
    model.addAttribute("wish", wish);
    return "test/new_wish";
  }

  @PostMapping("/saveWish")
  public String saveWish(@ModelAttribute("wish") Wish wish, HttpSession session) {
    long id = (long) session.getAttribute("WLIdSession");
    // save wish to database
    wish.setWishlist(wishlistService.getWishlistById(id).get());
    wishService.saveWish(wish);
    return "redirect:/wishlist_homepage";
  }

  @PostMapping("/saveWishAfterUpdate")
  public String saveWishAfterUpdate(@ModelAttribute("wish") Wish wish, HttpSession session) {
    long id = (long) session.getAttribute("WLIdSession");
    // save wish to database
    wish.setWishlist(wishlistService.getWishlistById(id).get());
    wishService.saveWish(wish);

    // delete old wish from database
    long idUpdate = (long) session.getAttribute("WishIdForUpdateSession");
    this.wishService.deleteWishById(idUpdate);

    return "redirect:/wishlist_homepage";
  }

  @GetMapping("/showWishFormForUpdate/{id}")
  public String showFormForUpdate(@PathVariable(value = "id") long id, Model model, HttpSession session) {
    session.setAttribute("WishIdForUpdateSession", id);
    // get wish from the service
    Wish wish = wishService.getWishById(id).get();

    // set wish as a model attribute to pre-populate the form
    model.addAttribute("wish", wish);
    return "test/update_wish";
  }

  @GetMapping("/deleteWish/{id}")
  public String deleteWish(@PathVariable(value = "id") long id) {

    // call delete wish method
    this.wishService.deleteWishById(id);
    return "redirect:/wishlist_homepage";
  }

/*  @PutMapping(path = "/put/title/{wishId}")
  public void updateWishTitle(
      @PathVariable("wishId") Long id,
      @RequestParam(required = false) String title) {
    wishService.updateTitle(id, title);
  }

  @PutMapping(path = "/put/descr/{wishId}")
  public void updateWishDescr(
      @PathVariable("wishId") Long id,
      @RequestParam(required = false) String descr) {
    wishService.updateDescription(id, descr);
  }

  @PutMapping(path = "/put/price/{wishId}")
  public void updateWishPrice(
      @PathVariable("wishId") Long id,
      @RequestParam(required = false) Integer price) {
    wishService.updatePrice(id, price);
  }

  @PutMapping(path = "/put/link/{wishId}")
  public void updateWishLink(
      @PathVariable("wishId") Long id,
      @RequestParam(required = false) String link) {
    wishService.updateLink(id, link);
  }*/
}
