package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.repository.UserRepository;
import com.example.digitalwishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

  private final WishlistRepository wishlistRepository;
  private final UserRepository userRepository;


  @Autowired
  public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository) {
    this.wishlistRepository = wishlistRepository;
    this.userRepository = userRepository;
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

  public List<Wishlist> getAllWishlistsByUser( String email) {
    Optional<User> user = userRepository.findById(email);
    return wishlistRepository.findAllByUser(user);
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
