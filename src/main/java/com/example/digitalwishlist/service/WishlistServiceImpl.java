package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

  private final WishlistRepository wishlistRepository;

  @Autowired
  public WishlistServiceImpl(WishlistRepository wishlistRepository) {
    this.wishlistRepository = wishlistRepository;
  }

  @Override
  public void saveWishlist(Wishlist wishlist) {
    this.wishlistRepository.save(wishlist);
  }

  @Override
  public void deleteWishlistById(long id) {
    boolean exists = wishlistRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("Wishlist with id:  " + id + " does not exist");
    }
    this.wishlistRepository.deleteById(id);
  }

  @Override
  public List<Wishlist> getAllWishlists() {
    return wishlistRepository.findAll();
  }

  @Override
  public Optional<Wishlist> getWishlistById(long id) {
    Optional<Wishlist> optional = wishlistRepository.findById(id);
    Wishlist wishlist;
    if (optional.isPresent()) {
      wishlist = optional.get();
    } else {
      throw new RuntimeException("Wishlist not found for id :: " + id);
    }
    return Optional.of(wishlist);
  }

  @Override
  public List<Wishlist> getWishlistsByUserId(User user) {
    List<Wishlist> unsortedList = wishlistRepository.findAll();
    List<Wishlist> sortedList = new ArrayList<>();
    String userId = user.getEmail();
    String foreignKey;
    for (int i = 0; i < unsortedList.size(); i++) {
      foreignKey = unsortedList.get(i).getUser().getEmail();
      if (foreignKey.equals(userId)) {
        Wishlist wishlist = unsortedList.get(i);
        sortedList.add(wishlist);
      }
    }
    return sortedList;
  }

  @Override
  public List<Wishlist> prepareUserWishlists(String userId, List<Wish> allWishes) {
    List<Wishlist> userWishlists = new ArrayList<>();
    List<Wishlist> allWishlists = getAllWishlists();

    for (int i = 0; i < allWishlists.size(); i++) {

      Wishlist wishlistToAdd = allWishlists.get(i);
      String wishlistForeignKey = wishlistToAdd.getUser().getEmail();


      if (wishlistForeignKey.equals(userId)) {
        userWishlists.add(wishlistToAdd);
        long addedWishlistId = wishlistToAdd.getId();

        for (Wish wishFromDatabase : allWishes) {
          long databaseWishForeignKey = wishFromDatabase.getWishlist().getId();

          if (databaseWishForeignKey == addedWishlistId) {
            long databaseWishId = wishFromDatabase.getId();
            wishlistToAdd.setWish(wishFromDatabase);
            wishlistToAdd.removeDuplicatedWishes(databaseWishId);
          }
        }
      }
    }
    return userWishlists;
  }

/*  @Transactional
  public void updateTitle(long id, String title) {
    Wishlist wishlist = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wishlist with id:  " + id + " does not exist"));

    if (title != null && !title.equals(wishlist.getTitle())) {
      wishlist.setTitle(title);
    }
  }

  @Transactional
  public void updateDescription(long id, String descr) {
    Wishlist wishlist = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wishlist with id:  " + id + " does not exist"));

    if (descr != null && !descr.equals(wishlist.getDescr())) {
      wishlist.setDescr(descr);
    }
  }*/

}
