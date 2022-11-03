package com.example.digitalwishlist.repository;

import com.example.digitalwishlist.entity.User;
import com.example.digitalwishlist.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

  List<Wishlist> findAllByIdAndUserEmail(Long id, String email);

  List<Wishlist> findAllByUser(Optional<User> user);

  Page<Wishlist> findAllByIdAndUserEmail(Long id, String email, Pageable page);
}
