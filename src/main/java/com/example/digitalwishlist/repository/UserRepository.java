package com.example.digitalwishlist.repository;

import com.example.digitalwishlist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

/*  public User findByUserId(String email);*/
}
