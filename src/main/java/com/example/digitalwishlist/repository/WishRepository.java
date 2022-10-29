package com.example.digitalwishlist.repository;

import com.example.digitalwishlist.model.Wish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends CrudRepository<Wish, Integer> {
}
