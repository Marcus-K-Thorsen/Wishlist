package com.example.digitalwishlist;

import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.digitalwishlist")
public class DigitalWishlistApplication {

  public static void main(String[] args) {
    SpringApplication.run(DigitalWishlistApplication.class, args);

    // Test data der indsættes i databasen hver gang applikationen køres
    User user1 = new User();
    user1.setEmail("user01@kea.com");
    user1.setGivenName("Martin");
    user1.setLastName("Johnson");
    user1.setPassword("JohnnyBoy123");
    UserService.save(user1);

    User user2 = new User();
    user2.setEmail("user02@kea.com");
    user2.setGivenName("Søren");
    user2.setLastName("Johnson");
    user2.setPassword("JohnnyBoy123");
    UserService.save(user2);
  }
}

