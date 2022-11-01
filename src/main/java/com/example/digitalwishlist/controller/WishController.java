package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.service.WishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/Wish")
public class WishController {

  private final WishServiceImpl wishService;

  @Autowired
  public WishController(WishServiceImpl wishService) {
    this.wishService = wishService;
  }

  // display list of wishes
  @GetMapping("/")
  public String viewHomePage(Model model) {
    model.addAttribute("listWishes", wishService.getAllWishes());
    return "index";
  }

  @GetMapping("/showNewWishForm")
  public String showNewWishForm(Model model) {
    // create model attribute to bind form data
    Wish wish = new Wish();
    model.addAttribute("wish", wish);
    return "new_wish";
  }

  @PostMapping("/saveWish")
  public String saveWish(@ModelAttribute("wish") Wish wish) {
    // save wish to database
    wishService.saveWish(wish);
    return "redirect:/";
  }

  @GetMapping("/showFormForUpdate/{id}")
  public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

    // get wish from the service
    Optional<Wish> wish = wishService.getWishById(id);

    // set wish as a model attribute to pre-populate the form
    model.addAttribute("wish", wish);
    return "update_wish";
  }

  @GetMapping("/deleteWish/{id}")
  public String deleteWish(@PathVariable(value = "id") long id) {

    // call delete wish method
    this.wishService.deleteWishById(id);
    return "redirect:/";
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
