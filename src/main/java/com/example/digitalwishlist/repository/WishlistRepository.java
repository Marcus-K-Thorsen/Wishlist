package com.example.digitalwishlist.repository;

import com.example.digitalwishlist.model.Wishlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
}
