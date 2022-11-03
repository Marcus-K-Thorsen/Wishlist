package com.example.digitalwishlist.model.service;

import com.example.digitalwishlist.model.entity.Wish;

import java.util.List;
import java.util.Optional;

public interface WishService {
  void saveWish(Wish wish);

  void deleteWishById(long id);

  List<Wish> getAllWishes();

  Optional<Wish> getWishById(long id);
}
