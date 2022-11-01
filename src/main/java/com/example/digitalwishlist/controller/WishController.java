package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wish")
public class WishController {

  private final WishService wishService;

  @Autowired
  public WishController(WishService wishService) {
    this.wishService = wishService;
  }

  //TODO : fix get og getAll metoder

/*  @GetMapping(path = "/get")
  public List<Wish> getWish() {
    return wishService.getWish();
  }

  @GetMapping(path = "/getAll")
  public List<Wish> getAllWishes() {
    return wishService.getAllWishes();
  }*/

  @PostMapping(path = "/post")
  public void registerNewWish(@RequestBody Wish wish) {
    wishService.save(wish);
  }

  @DeleteMapping(path = "/delete/{wishId}")
  public void deleteWish(@PathVariable("wishId") Long id) {
    wishService.delete(id);
  }

  @PutMapping(path = "/put/title/{wishId}")
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
  }
}
