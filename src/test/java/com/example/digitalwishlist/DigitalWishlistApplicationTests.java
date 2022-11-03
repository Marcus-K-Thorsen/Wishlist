package com.example.digitalwishlist;

import com.example.digitalwishlist.model.entity.User;
import com.example.digitalwishlist.model.entity.Wish;
import com.example.digitalwishlist.model.entity.Wishlist;
import com.example.digitalwishlist.model.service.UserServiceImpl;
import com.example.digitalwishlist.model.service.WishServiceImpl;
import com.example.digitalwishlist.model.service.WishlistServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DigitalWishlistApplicationTests {

  @Autowired
  private UserServiceImpl userService;
  @Autowired
  private WishlistServiceImpl wishlistService;
  @Autowired
  private WishServiceImpl wishService;

/*  @Test*/
  void contextLoads() {
  }
  @Test
  void fakeData() {
    User user1 = new User();
    user1.setEmail("user01@kea.com");
    user1.setGivenName("Martin");
    user1.setLastName("Johnson");
    user1.setPassword("JohnnyBoy123");
    userService.saveUser(user1);

    User user2 = new User();
    user2.setEmail("user02@kea.com");
    user2.setGivenName("Søren");
    user2.setLastName("Johnson");
    user2.setPassword("JohnnyBoy123");
    userService.saveUser(user2);

    Wishlist wishlist1 = new Wishlist();
    wishlist1.setTitle("Julegaver");
    wishlist1.setDescr("jul");
    wishlist1.setUser(user1);
    wishlistService.saveWishlist(wishlist1);

    Wishlist wishlist2 = new Wishlist();
    wishlist2.setTitle("fødselsdag");
    wishlist2.setDescr("dag");
    wishlist2.setUser(user2);
    wishlistService.saveWishlist(wishlist2);

    Wishlist wishlist3 = new Wishlist();
    wishlist3.setTitle("julegaver");
    wishlist3.setDescr("xmas");
    wishlist3.setUser(user2);
    wishlistService.saveWishlist(wishlist3);

    Wish wish1 = new Wish();
    wish1.setTitle("Træ hest");
    wish1.setDescr("hest af træ");
    wish1.setPrice(49.99);
    wish1.setLink("træhest.dk");
    wish1.setWishlist(wishlist1);
    wishService.saveWish(wish1);

    Wish wish2 = new Wish();
    wish2.setTitle("Træ And");
    wish2.setDescr("and af træ");
    wish2.setPrice(51.99);
    wish2.setLink("træand.dk");
    wish2.setWishlist(wishlist2);
    wishService.saveWish(wish2);

    Wish wish3 = new Wish();
    wish3.setTitle("Træ elefant");
    wish3.setDescr("elefant af træ");
    wish3.setPrice(55.99);
    wish3.setLink("træelefant.dk");
    wish3.setWishlist(wishlist2);
    wishService.saveWish(wish3);

    Wish wish4 = new Wish();
    wish4.setTitle("Træ abe");
    wish4.setDescr("abe af træ");
    wish4.setPrice(6000.99);
    wish4.setLink("træabe.dk");
    wish4.setWishlist(wishlist3);
    wishService.saveWish(wish4);

    Wish wish5 = new Wish();
    wish5.setTitle("Træ ugle");
    wish5.setDescr("ugle af træ");
    wish5.setPrice(77.99);
    wish5.setLink("træugle.dk");
    wish5.setWishlist(wishlist3);
    wishService.saveWish(wish5);

    Wish wish6 = new Wish();
    wish6.setTitle("Træ giraf");
    wish6.setDescr("giraf af træ");
    wish6.setPrice(6660.99);
    wish6.setLink("trægiraf.dk");
    wish6.setWishlist(wishlist3);
    wishService.saveWish(wish6);
  }
}
