package com.example.digitalwishlist.repository;

import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

  List<Wish> findAllByWishlist(Optional<Wishlist> wishlist);
}
