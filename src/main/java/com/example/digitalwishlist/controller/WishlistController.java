package com.example.digitalwishlist.controller;


import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class WishlistController {

  private final WishlistService wishlistService;

  @Autowired
  public WishlistController(WishlistService wishlistService) {
    this.wishlistService = wishlistService;
  }

  @GetMapping(path = "/wishlist/get")
  public List<Wishlist> getWishlist() {
    return wishlistService.getWishlist();
  }

  @GetMapping(path = "/wishlist/getAll")
  public List<Wishlist> getAllWishlists() {
    return wishlistService.getAllWishlists();
  }

  @PostMapping(path = "/wishlist/post")
  public void registerNewWishlist(@RequestBody Wishlist wishlist) {
    wishlistService.save(wishlist);
  }

  @DeleteMapping(path = "/wishlist/delete/{userId}")
  public void deleteWishlist(@PathVariable("userId") Integer id) {
    wishlistService.delete(id);
  }

  @PutMapping(path = "/wishlist/put/title/{userId}")
  public void updateWishlistTitle(
      @PathVariable("userId") Integer id,
      @RequestParam(required = false) String title) {
    wishlistService.updateTitle(id, title);
  }

  @PutMapping(path = "/wishlist/put/descr/{userId}")
  public void updateWishlistDescr(
      @PathVariable("userId") Integer id,
      @RequestParam(required = false) String descr) {
    wishlistService.updateDescription(id, descr);
  }
}
