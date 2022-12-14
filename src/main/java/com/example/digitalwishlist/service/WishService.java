package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.model.Wishlist;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

public interface WishService {
  void saveWish(Wish wish);

  void deleteWishById(long id);

  List<Wish> getAllWishes();

  Optional<Wish> getWishById(long id);

  Wish createWishFromWebReq(WebRequest webRequest, Wishlist wishlist);
}
