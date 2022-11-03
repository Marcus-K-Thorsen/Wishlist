package com.example.digitalwishlist.model.repository;

import com.example.digitalwishlist.model.entity.Wish;
import com.example.digitalwishlist.model.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

  List<Wish> findAllByWishlist(Optional<Wishlist> wishlist);
}
