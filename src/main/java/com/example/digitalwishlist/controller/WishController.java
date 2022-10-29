package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class WishController {

  private final WishService wishService;

  @Autowired
  public WishController(WishService wishService) {
    this.wishService = wishService;
  }

  @GetMapping(path = "/wish/get")
  public List<Wish> getWish() {
    return wishService.getWish();
  }

  @GetMapping(path = "/wish/getAll")
  public List<Wish> getAllWishes() {
    return wishService.getAllWishes();
  }

  @PostMapping(path = "/wish/post")
  public void registerNewWish(@RequestBody Wish wish) {
    WishService.save(wish);
  }

  @DeleteMapping(path = "/wish/delete/{userId}")
  public void deleteWish(@PathVariable("userId") Integer id) {
    wishService.delete(id);
  }

  @PutMapping(path = "/wish/put/title/{userId}")
  public void updateWishTitle(
      @PathVariable("userId") Integer id,
      @RequestParam(required = false) String title) {
    wishService.updateTitle(id, title);
  }

  @PutMapping(path = "/wish/put/descr/{userId}")
  public void updateWishDescr(
      @PathVariable("userId") Integer id,
      @RequestParam(required = false) String descr) {
    wishService.updateDescription(id, descr);
  }

  @PutMapping(path = "/wish/put/price/{userId}")
  public void updateWishPrice(
      @PathVariable("userId") Integer id,
      @RequestParam(required = false) Integer price) {
    wishService.updatePrice(id, price);
  }

  @PutMapping(path = "/wish/put/link/{userId}")
  public void updateWishLink(
      @PathVariable("userId") Integer id,
      @RequestParam(required = false) String link) {
    wishService.updateLink(id, link);
  }
}
