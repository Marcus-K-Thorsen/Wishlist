package com.example.digitalwishlist.controller;

import com.example.digitalwishlist.model.DTO;
import com.example.digitalwishlist.model.User;
import com.example.digitalwishlist.model.Wish;
import com.example.digitalwishlist.model.Wishlist;
import com.example.digitalwishlist.service.UserServiceImpl;
import com.example.digitalwishlist.service.WishServiceImpl;
import com.example.digitalwishlist.service.WishlistServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class LoginController {

  private final UserServiceImpl userService;
  private final WishlistServiceImpl wishlistService;
  private final WishServiceImpl wishService;

  public LoginController(UserServiceImpl userService, WishlistServiceImpl wishlistService, WishServiceImpl wishService) {
    this.userService = userService;
    this.wishlistService = wishlistService;
    this.wishService = wishService;
  }

  @PostMapping("/login-submit")
  public String login(Model model, WebRequest req) {
    String userId = req.getParameter("userId");
    String password = req.getParameter("password");

    boolean test = userService.loginTest(userId, password);
    if (test) {
      try {
        User user = prepareUser(req);
        if (user != null) {
          List<Wishlist> userWishlists = user.getWishlists();
          model.addAttribute("user", user);
          model.addAttribute("wishlists", userWishlists);
          return "view-user";
        }
      } catch (RuntimeException e) {
        System.out.println(e);
        System.out.println("Noget gik galt i login metoden der er i klassen LoginController");
      }
    }
    return "redirect:/signup-fail";
  }


  @PostMapping("/create-wishlist")
  public String createWishlist(Model model, WebRequest webRequest) {
    String userId = webRequest.getParameter("userId");
    try {
      User user = prepareUser(userId);
      model.addAttribute("user", user);
      return "create_wishlist";

    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("Noget gik galt i metoden createWishlist inde i klassen WishlistController");
    }
    model.addAttribute("fail-message", "Noget gik galt med at create en wishlist.");
    return "redirect:/complete-fail";
  }

  @PostMapping("/regret-wishlist")
  public String regretWishlist(Model model, WebRequest webRequest) {
    String userId = webRequest.getParameter("userId");
    User user = prepareUser(userId);
    assert user != null;
    List<Wishlist> userWishlists = user.getWishlists();
    model.addAttribute("user", user);
    model.addAttribute("wishlists", userWishlists);
    return "view-user";
  }

  @PostMapping("/wishlist-created")
  public String createdWishlist(Model model, WebRequest webRequest) {
    String wishlistTitle = webRequest.getParameter("wishlistTitle");
    String wishlistDescr = webRequest.getParameter("wishlistDescr");
    String userId = webRequest.getParameter("userId");
    User user = prepareUser(userId);
    Wishlist newWishlist = new Wishlist(user, wishlistTitle, wishlistDescr);
    assert user != null;
    user.setWishlists(newWishlist);
    wishlistService.saveWishlist(newWishlist);
    List<Wishlist> userWishlists = user.getWishlists();
    model.addAttribute("user", user);
    model.addAttribute("wishlists", userWishlists);
    return "view-user";
  }

  @PostMapping("/delete-wishlist")
  public String deleteWishlist(Model model, WebRequest webRequest) {
    String userId = webRequest.getParameter("userId");
    String wishlistId = webRequest.getParameter("wishlistId");
    long wishlistIdAsLong = Long.parseLong(webRequest.getParameter("wishlistId"));

    System.out.println("DER FORSØGES AT FJERNE WISHLIST ID " + wishlistId + " OG SOM EN LONG: " + wishlistIdAsLong);
    wishlistService.deleteWishlistById(wishlistIdAsLong);
    User user = prepareUser(userId);
    assert user != null;
    List<Wishlist> userWishlists = user.getWishlists();
    model.addAttribute("user", user);
    model.addAttribute("wishlists", userWishlists);
    return "view-user";
  }

  @PostMapping("/see-wishlist")
  public String seeWishlist(Model model, WebRequest webRequest) {
    String userId = webRequest.getParameter("userId");
    String wishlistId = webRequest.getParameter("wishlistId");
    User user = prepareUser(userId);
    if (user != null) {
      Wishlist wishlist = user.getWishlist(wishlistId);
      model.addAttribute("user", user);
      if (wishlist != null) {
        model.addAttribute("wishlist", wishlist);
        List<Wish> wishes = wishlist.getWishes();
        model.addAttribute("wishes", wishes);
        return "view-wishlist";
      }
      List<Wishlist> wishlists = user.getWishlists();
      model.addAttribute("wishlists", wishlists);
      return "view-user";
    }
    String failMessage = "Noget gik galt ved at se en ønskeliste og useren er også blevet væk, begge er null";
    model.addAttribute("message", failMessage);

    return "redirect:/complete-fail";
  }

  @PostMapping("/edit-wishlist-title")
  public String editWishlistTitle(Model model, WebRequest webRequest) {
    User user = prepareUser(webRequest);

    if (user != null) {
      model.addAttribute("user", user);
      String wishlistId = webRequest.getParameter("wishlistId");
      Wishlist wishlistToEdit = user.getWishlist(wishlistId);
      if (wishlistToEdit != null) {
        String wishlistTitleInput = webRequest.getParameter("wishlistTitle");
        wishlistToEdit.setTitle(wishlistTitleInput);
        wishlistService.saveWishlist(wishlistToEdit);
        model.addAttribute("wishlist", wishlistToEdit);
        List<Wish> wishes = wishlistToEdit.getWishes();
        model.addAttribute("wishes", wishes);
        return "view-wishlist";
      }
      List<Wishlist> wishlists = user.getWishlists();
      model.addAttribute("wishlists", wishlists);
      return "view-user";
    }
    String failMessage = "Noget gik galt ved at edit en ønskelistes titel og useren er også blevet væk, begge er null";
    model.addAttribute("message", failMessage);
    return "redirect:/complete-fail";
  }

  @PostMapping("/edit-wishlist-desc")
  public String editWishlistDescription(Model model, WebRequest webRequest) {
    User user = prepareUser(webRequest);
    if (user != null) {
      String wishlistId = webRequest.getParameter("wishlistId");
      Wishlist wishlistToEdit = user.getWishlist(wishlistId);
      model.addAttribute("user", user);
      if (wishlistToEdit != null) {
        String wishlistDescInput = webRequest.getParameter("wishlistDesc");
        wishlistToEdit.setDescr(wishlistDescInput);
        wishlistService.saveWishlist(wishlistToEdit);
        model.addAttribute("wishlist", wishlistToEdit);
        List<Wish> wishes = wishlistToEdit.getWishes();
        model.addAttribute("wishes", wishes);
        return "view-wishlist";
      }
      List<Wishlist> wishlists = user.getWishlists();
      model.addAttribute("wishlists", wishlists);
      return "view-user";
    }
    String failMessage = "Noget gik galt ved at edit en ønskelistes beskrivelse og useren er også blevet væk, begge er null";
    model.addAttribute("message", failMessage);
    return "redirect:/complete-fail";
  }

  @PostMapping("/create-wish")
  public String createWish(Model model, WebRequest webRequest) {
    User user = prepareUser(webRequest);
    if (user != null) {
      String wishlistId = webRequest.getParameter("wishlistId");
      Wishlist wishlist = user.getWishlist(wishlistId);
      if (wishlist != null) {
        model.addAttribute("user", user);
        model.addAttribute("wishlist", wishlist);
        return "create_wish";
      }
      List<Wishlist> wishlists = user.getWishlists();
      model.addAttribute("wishlists", wishlists);
      return "view-user";
    }
    String failMessage = "Noget gik galt ved at skabe et ønske til en ønskeliste og useren er også blevet væk, både user og wishlist er null";
    model.addAttribute("message", failMessage);
    return "redirect:/complete-fail";
  }

  @PostMapping("/regret-wish")
  public String regretWish(Model model, WebRequest webRequest) {
    User user = prepareUser(webRequest);
    if (user != null) {
      model.addAttribute("user", user);
      String wishlistId = webRequest.getParameter("wishlistId");
      Wishlist wishlist = user.getWishlist(wishlistId);
      if (wishlist != null) {
        model.addAttribute("wishlist", wishlist);
        List<Wish> wishes = wishlist.getWishes();
        model.addAttribute("wishes", wishes);
        return "view-wishlist";
      }
      List<Wishlist> wishlists = user.getWishlists();
      model.addAttribute("wishlists", wishlists);
      return "view-user";
    }
    String failMessage = "Noget gik galt ved at fortryde at skabe et ønske til en ønskeliste og useren er også blevet væk, både user og wishlist er null";
    model.addAttribute("message", failMessage);
    return "redirect:/complete-fail";
  }

  @PostMapping("/wish-created")
  public String wishCreated(Model model, WebRequest webRequest) {
    User user = prepareUser(webRequest);
    if (user != null) {
      model.addAttribute("user", user);
      String wishlistId = webRequest.getParameter("wishlistId");
      Wishlist wishlist = user.getWishlist(wishlistId);
      if (wishlist != null) {
        Wish createdWish = wishService.createWishFromWebReq(webRequest, wishlist);
        ;
        wishService.saveWish(createdWish);
        List<Wish> wishes = wishlist.getWishes();
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishes", wishes);
        return "view-wishlist";
      }
      List<Wishlist> wishlists = user.getWishlists();
      model.addAttribute("wishlists", wishlists);
      return "view-user";
    }
    String failMessage = "Noget gik galt ved at skabe et ønske til en ønskeliste og useren er også blevet væk, både user og wishlist er null";
    model.addAttribute("message", failMessage);
    return "redirect:/complete-fail";
  }

  @PostMapping("/remove-wish")
  public String removeWish(Model model, WebRequest webRequest) {

    String wishId = webRequest.getParameter("wishId");
    if (wishId != null && !wishId.isBlank()) {
      long wishToRemoveId = Long.parseLong(wishId);
      Optional<Wish> optionalWish = wishService.getWishById(wishToRemoveId);

      if (optionalWish.isPresent()) {
        wishService.deleteWishById(wishToRemoveId);
        User user = prepareUser(webRequest);
        if (user != null) {
          model.addAttribute("user", user);
          String wishlistId = webRequest.getParameter("wishlistId");
          Wishlist wishlist = user.getWishlist(wishlistId);

          if (wishlist != null) {
            model.addAttribute("wishlist", wishlist);
            List<Wish> wishes = wishlist.getWishes();
            model.addAttribute("wishes", wishes);
            return "view-wishlist";
          }
          List<Wishlist> wishlists = user.getWishlists();
          model.addAttribute("wishlists", wishlists);
          return "view-user";
        }
        String failMessage = "Noget gik galt ved at slette et ønske fra en ønskeliste og useren er også blevet væk, både user og wishlist er null";
        model.addAttribute("message", failMessage);
        return "redirect:/complete-fail";
      }
    }
    String failMessage = "Noget gik galt ved at slette et ønske fra en ønskeliste da ønsket der skulle slettes ikke eksisterer";
    model.addAttribute("message", failMessage);
    return "redirect:/complete-fail";
  }

  @GetMapping("/share-wishlist")
  public String shareWishlist (@ModelAttribute DTO data, Model model) {
    String wishlistId = data.getWishlistId();
    if (wishlistId != null && !wishlistId.isBlank()) {
      long wishlistIdNum;
      Optional<Wishlist> optionalWishlist;
      try {
        wishlistIdNum = Long.parseLong(wishlistId);
        optionalWishlist = wishlistService.getWishlistById(wishlistIdNum);
      } catch (Exception e) {

        String failMessage = "Noget gik galt ved at finde en delt ønskeliste da det indførte ønske liste nummer: " + wishlistId + " ikke er et tal";
        DTO failData = new DTO(failMessage);
        model.addAttribute("message", failData);
        return "redirect:/complete-fail";
      }
      if (optionalWishlist.isPresent()) {
        Wishlist wishlist = optionalWishlist.get();
        String userId = wishlist.getUser().getEmail();
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
          User user = optionalUser.get();
          List<Wish> wishes = wishlist.getWishes();
          model.addAttribute("user", user);
          model.addAttribute("wishlist", wishlist);
          model.addAttribute("wishes", wishes);
          return "share_wishlist";
        }
      }
    }
    String failMessage = "Noget gik galt ved at finde en delt ønskeliste da det indførte ønske liste nummer: " + wishlistId + " ikke er mellem listerne i databasen";
    model.addAttribute("message", failMessage);
    return "redirect:/complete-fail";
  }


  private User prepareUser(WebRequest webReqUserId) {
    String userId = webReqUserId.getParameter("userId");
    return prepareUser(userId);
  }

  private User prepareUser(String userId) {


    try {
      Optional<User> optionalUser = userService.getUserById(userId);
      if (optionalUser.isPresent()) {
        User user = optionalUser.get();

        List<Wish> allWishes = wishService.getAllWishes();
        List<Wishlist> userWishlists = wishlistService.prepareUserWishlists(userId, allWishes);

        System.out.println(user);
        user.setWishlists(userWishlists);


        return user;
      }
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("Noget gik galt i prepareUser metoden der er i klassen LoginController");
    }
    return null;
  }
}
