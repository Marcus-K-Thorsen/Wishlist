package com.example.digitalwishlist.model.wishlist;

import com.example.digitalwishlist.model.wish.Wish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
}
