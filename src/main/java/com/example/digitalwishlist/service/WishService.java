package com.example.digitalwishlist.service;

import com.example.digitalwishlist.entity.Wish;

import java.util.List;
import java.util.Optional;

public interface WishService {
  void saveWish(Wish wish);

  void deleteWishById(long id);

  List<Wish> getAllWishes();

  Optional<Wish> getWishById(long id);
}
