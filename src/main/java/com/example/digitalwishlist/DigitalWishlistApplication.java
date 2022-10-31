package com.example.digitalwishlist;

import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.service.UserService;
import com.example.digitalwishlist.service.WishService;
import com.example.digitalwishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.digitalwishlist")
public class DigitalWishlistApplication {

  public static void main(String[] args) {
    SpringApplication.run(DigitalWishlistApplication.class, args);
  }
}

