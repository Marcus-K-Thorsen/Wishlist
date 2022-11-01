package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishServiceImpl implements WishService {

  private final WishRepository wishRepository;

  @Autowired
  public WishServiceImpl(WishRepository repository) {
    this.wishRepository = repository;
  }

  @Override
  public void saveWish(Wish wish) {
    this.wishRepository.save(wish);
  }

  @Override
  public void deleteWishById(long id) {
    boolean exists = wishRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("Wish with id:  " + id + " does not exist");
    }
    this.wishRepository.deleteById(id);
  }

  @Override
  public List<Wish> getAllWishes() {
    return wishRepository.findAll();
  }

  @Override
  public Optional<Wish> getWishById(long id) {
    Optional<Wish> optional = wishRepository.findById(id);
    Wish wish;
    if (optional.isPresent()) {
      wish = optional.get();
    } else {
      throw new RuntimeException("Wish not found for id :: " + id);
    }
    return Optional.of(wish);
  }

/*  @Transactional
  public void updateTitle(long id, String title) {
    Wish wish = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wish with id:  " + id + " does not exist"));

    if (title != null && !title.equals(wish.getTitle())) {
      wish.setTitle(title);
    }
  }

  @Transactional
  public void updateDescription(long id, String descr) {
    Wish wish = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wish with id:  " + id + " does not exist"));

    if (descr != null && !descr.equals(wish.getDescr())) {
      wish.setDescr(descr);
    }
  }

  @Transactional
  public void updatePrice(long id, int price) { //Integer?? eller long??
    Wish wish = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wish with id:  " + id + " does not exist"));

    if (price > 0 && price != wish.getPrice()) { // price != null &&
      wish.setPrice(price);
    }
  }

  @Transactional
  public void updateLink(long id, String link) {
    Wish wish = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wish with id:  " + id + " does not exist"));

    if (link != null && !link.equals(wish.getLink())) {
      wish.setLink(link);
    }
  }*/
}
