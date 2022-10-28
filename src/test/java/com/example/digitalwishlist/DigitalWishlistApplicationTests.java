package com.example.digitalwishlist;

import com.example.digitalwishlist.model.user.User;
import com.example.digitalwishlist.model.user.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DigitalWishlistApplicationTests {

  @Autowired
  private UserDao userDao;

/*  @Test
  void addUserTest() {
    User user = new User();
    user.setEmail("user01@kea.com");
    user.setGivenName("John");
    user.setLastName("Johnson");
    user.setPassword("JohnnyBoy123");
    UserDao.save(user);
  }*/

/*  @Test
  void getAllUsersAndDeleteThem() {
    List<User> users = userDao.getAllUsers();
    System.out.println(users);
    for (User user : users) {
      userDao.delete(user);
    }
  }*/
/*@Test
  void deleteUserById () {
    userDao.delete("user02@kea.com");
  }*/

  @Test
  void changePasswordById(){
    userDao.updatePassword("user01@kea.com","JohnJohn");
  }
/*@Test
  void contextLoads() {
  }*/
}
