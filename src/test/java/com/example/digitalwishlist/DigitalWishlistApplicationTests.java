package com.example.digitalwishlist;

import com.example.digitalwishlist.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DigitalWishlistApplicationTests {

  @Autowired
  private UserService userService;

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
    userService.updatePassword("user01@kea.com","JohnJohn");
  }
/*@Test
  void contextLoads() {
  }*/
}
