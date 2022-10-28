package com.example.digitalwishlist.model.wish;

import com.example.digitalwishlist.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends CrudRepository<Wish, Integer> {
}
