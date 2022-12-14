package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.model.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
  void saveWishlist(Wishlist wishlist);

  void deleteWishlistById(long id);

  List<Wishlist> getAllWishlists();

  Optional<Wishlist> getWishlistById(long id);

  List<Wishlist> getWishlistsByUserId (User user);

  List<Wishlist> prepareUserWishlists(String brugernavn, List<Wish> allWishes);
}
