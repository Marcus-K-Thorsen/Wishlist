package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

  private final WishlistService wishlistService;

  @Autowired
  public WishlistController(WishlistService wishlistService) {
    this.wishlistService = wishlistService;
  }

  //TODO : fix get og getAll metoder

/*  @GetMapping(path = "/get")
  public List<Wishlist> getWishlist() {
    return wishlistService.getWishlist();
  }

  @GetMapping(path = "/getAll")
  public List<Wishlist> getAllWishlists() {
    return wishlistService.getAllWishlists();
  }*/

  @PostMapping(path = "/post")
  public void registerNewWishlist(@RequestBody Wishlist wishlist) {
    wishlistService.save(wishlist);
  }

  @DeleteMapping(path = "/delete/{wishlistId}")
  public void deleteWishlist(@PathVariable("wishlistId") Integer id) {
    wishlistService.delete(id);
  }

  @PutMapping(path = "/put/title/{wishlistId}")
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
  }
}
