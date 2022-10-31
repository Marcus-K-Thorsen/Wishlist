package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

  private final WishlistRepository repository;

  @Autowired
  public WishlistService(WishlistRepository repository) {
    this.repository = repository;
  }

  public void save(Wishlist wishlist) {
    repository.save(wishlist);
  }

  public void delete(long id) {
    boolean exists = repository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("Wishlist with id:  " + id + " does not exist");
    }
    repository.deleteById(id);
  }

  public List<Wishlist> getAllWishlists() {
    List<Wishlist> wishlists = new ArrayList<>();
    Streamable.of(repository.findAll())
        .forEach(wishlists::add);
    return wishlists;
  }

  public List<Wishlist> getWishlist() {
    return (List<Wishlist>) repository.findAll();
  }

  @Transactional
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
  }

}
