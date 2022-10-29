package com.example.digitalwishlist.service;

import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishService {

  private static WishRepository repository = null;

  @Autowired
  public WishService(WishRepository repository) {
    WishService.repository = repository;
  }

  public static void save(Wish wish) {
    repository.save(wish);
  }

  public void delete(int id) {
    boolean exists = repository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("Wish with id:  " + id + " does not exist");
    }
    repository.deleteById(id);
  }

  public List<Wish> getAllWishes() {
    List<Wish> wishes = new ArrayList<>();
    Streamable.of(repository.findAll())
        .forEach(wishes::add);
    return wishes;
  }

  public List<Wish> getWish() {
    return (List<Wish>) repository.findAll();
  }

  @Transactional
  public void updateTitle(int id, String title) {
    Wish wish = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wish with id:  " + id + " does not exist"));

    if (title != null && !title.equals(wish.getTitle())) {
      wish.setTitle(title);
    }
  }

  @Transactional
  public void updateDescription(int id, String descr) {
    Wish wish = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wish with id:  " + id + " does not exist"));

    if (descr != null && !descr.equals(wish.getDescr())) {
      wish.setDescr(descr);
    }
  }

  @Transactional
  public void updatePrice(int id, int price) { //Integer?? eller long??
    Wish wish = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wish with id:  " + id + " does not exist"));

    if (price > 0 && price != wish.getPrice()) { // price != null &&
      wish.setPrice(price);
    }
  }

  @Transactional
  public void updateLink(int id, String link) {
    Wish wish = repository.findById(id).orElseThrow(() -> new IllegalStateException(
        "Wish with id:  " + id + " does not exist"));

    if (link != null && !link.equals(wish.getLink())) {
      wish.setLink(link);
    }
  }
}
