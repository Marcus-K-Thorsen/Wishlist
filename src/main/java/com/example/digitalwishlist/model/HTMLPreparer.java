package com.example.digitalwishlist.model;

import java.util.List;

public class HTMLPreparer {


  private static final String methodNameToSeeWishlist = "/se-ønskeseddel-side";
  private static final String methodNameToDeleteWishlist = "/slet-ønskeseddel-side";
  private static final String methodNameToCreateWishlist = "/slet-ønskeseddel-side";
  private static final String nameOfWishListID = "listID";
  private static final String nameOfWishID = "wishID";
  private static final String nameOfUserID = "userID";
  private static final String nameOfWishlistObject = "wishlist";
  private static final String nameOfUserObject = "user";

  private HTMLPreparer() {
  }

  public static String toDisplay4WishLists (User user) {
    StringBuilder collectedHTMLCode = new StringBuilder();
    List<Wishlist> wishlists = user.getWishlists();
    int amountOfWishlists = wishlists.size();
    String HTMLCode;
    for (int i = 1; i <= 4; i++) {
      if (i <= amountOfWishlists) {
        Wishlist wishlist = wishlists.get(i-1);
        HTMLCode = makeWishlistHTMLCode(wishlist);
      } else {
        HTMLCode = makeWishlistHTMLCode();
      }
      collectedHTMLCode.append(HTMLCode);
    }
    return collectedHTMLCode.toString();
  }

  public static String toDisplaySeeWishList(Wishlist wishlist) {
    StringBuilder collectedHTMLCode = new StringBuilder();
    String HTMLCode;
    List<Wish> wishes = wishlist.getWishes();
    int amountOfWishes = wishes.size();
    for (int i = 0; i < amountOfWishes; i++) {
      Wish wish = wishes.get(i);
      HTMLCode = makeAccountWishHTMLCode(wish, i);
      collectedHTMLCode.append(HTMLCode);
    }
    return collectedHTMLCode.toString();
  }

  public static String toDisplaySharedWishList(Wishlist wishlist) {
    StringBuilder collectedHTMLCode = new StringBuilder();
    String HTMLCode;
    List<Wish> wishes = wishlist.getWishes();
    int amountOfWishes = wishes.size();
    for (int i = 0; i < amountOfWishes; i++) {
      Wish wish = wishes.get(i);
      HTMLCode = makeSharedWishHTMLCode(wish, i);
      collectedHTMLCode.append(HTMLCode);
    }
    return collectedHTMLCode.toString();
  }



  // Privat metode for både toDisplaySeeWishList(Wishlist wishlist) og toDisplaySharedWishList(Wishlist wishlist)
  private static String makeWishLinkHTMLCode(Wish wish, int index) {
    String wishLink = wish.getLink();
    String wishLinkMessage = wish.displayLink();
    if (wishLink == null || wishLink.isBlank()) {
      return
          "<article class=\"wish-data wish-box\">\n" +
              "\t  " + wishLinkMessage + "\n" +
              "\t</article>";
    }
    return
        "<a class=\"wish-box åben-side wish-button\" th:href=\"${" + nameOfWishlistObject + ".getWish(" + index + ").getLink()}\" target=\"_blank\">\n" +
            "\t  " + wishLinkMessage + "\n" +
            "\t</a>";
  }

  // Privat metode for toDisplaySharedWishList(Wishlist wishlist)
  private static String makeSharedWishHTMLCode(Wish wish, int index) {
    String wishLinkAsHTML = makeWishLinkHTMLCode(wish, index);
    return
        "  <div class=\"shared-wish\">\n" +
            "    <article class=\"wish-data wish-box\" th:text=\"${" + nameOfWishlistObject + ".getWish(" + index + ").getTitle()}\">\n" +
            "    </article>\n" +
            "    \n" +
            "    <article class=\"wish-data wish-box\">\n" +
            "      Prisen for det ønskede produkt: <span class=\"inline\" th:text=\"${" + nameOfWishlistObject + ".getWish(" + index + ").displayPrice()}\"></span>\n" +
            "    </article>\n" +
            "    \n" +
            "    <article class=\"wish-data wish-box\">\n" +
            "      Beskrivelsen for dette ønske: <span class=\"inline\" th:text=\"${" + nameOfWishlistObject + ".getWish(" + index + ").displayDescr()}\"></span>\n" +
            "    </article>\n" +
            "    \n" +
            "    " + wishLinkAsHTML + "\n" +
            "  </div>\n";
  }

  // Privat metode for toDisplaySeeWishList(Wishlist wishlist)
  private static String makeAccountWishHTMLCode(Wish wish, int index) {
    String wishLinkAsHTML = makeWishLinkHTMLCode(wish, index);
    return
        "  <div class=\"wish\">\n" +
            "    <article class=\"wish-data wish-box\" th:text=\"${" + nameOfWishlistObject + ".getWish(" + index + ").getTitle()}\">\n" +
            "    </article>\n" +
            "\n" +
            "    <article class=\"wish-data wish-box\">\n" +
            "      Prisen for det ønskede produkt: <span class=\"inline\" th:text=\"${" + nameOfWishlistObject + ".getWish(" + index + ").displayPrice()}\"></span>\n" +
            "    </article>\n" +
            "\n" +
            "    <article class=\"wish-data wish-box\">\n" +
            "      Beskrivelsen for dette ønske: <span class=\"inline\" th:text=\"${" + nameOfWishlistObject + ".getWish(" + index + ").displayDescr()}\"></span> \n" +
            "    </article>\n" +
            "\n" +
            "   " + wishLinkAsHTML + "\n" +
            "\n" +
            "    <button type=\"submit\" form=\"rem-wish\" class=\"wish-box åben-side-med-input wish-button\">\n" +
            "      <input type=\"hidden\" name=\"" + nameOfWishID + "\" th:value=\"${" + nameOfWishlistObject + ".getWish(" + index + ").getId}\" form=\"rem-wish\">\n" +
            "      Slet dette ønske fra ønskelisten\n" +
            "    </button>\n" +
            "  </div>";
  }


  // Private metoder for toDisplay4WishLists(User user)
  private static String makeWishlistHTMLCode(Wishlist wishlist) {
    String HTMLCode = createBeginningHTMLForWishlist();
    HTMLCode += createMiddleHTMLForWishlist(wishlist);
    HTMLCode += createEndHTMLForWishlist();
    return HTMLCode;
  }

  private static String makeWishlistHTMLCode() {
    String HTMLCode = createBeginningHTMLForWishlist();
    HTMLCode += createMiddleHTMLForWishlist();
    HTMLCode += createEndHTMLForWishlist();
    return HTMLCode;
  }

  private static String createBeginningHTMLForWishlist() {
    return """
          \t
            <div class="wish-list">
              <div class="wish-icons grid-child">
            """;
  }
  private static String createMiddleHTMLForWishlist(Wishlist wishlist) {
    return
        "      <form method=\"POST\" action=\"" + methodNameToSeeWishlist + "\">\n" +
            "        <input type=\"hidden\" name=\"" + nameOfWishListID + "\" th:value=\"${" + nameOfWishlistObject + ".getId()}\">\n" +
            "        <button type=\"submit\" class=\"small-button åben-side-med-input\">\n" +
            "          Se Ønskeseddel\n" +
            "        </button>\n" +
            "      </form>\n" +
            "      <form method=\"POST\" action=\"" + methodNameToDeleteWishlist + "\">\n" +
            "        <input type=\"hidden\" name=\"" + nameOfWishListID + "\" th:value=\"${" + nameOfWishlistObject + ".getId()}\">\n" +
            "        <button type=\"submit\" class=\"small-button åben-side-med-input\">\n" +
            "          Slet Ønskeseddel\n" +
            "        </button>\n" +
            "      </form>\n" +
            "    </div>\n" +
            "    <article class=\"grid-child\">\n" +
            "      Titel:\n" +
            "      <div class=\"inline\" th:text=\"${" + nameOfWishlistObject + ".getTitle()}\">\n" +
            "      </div>\n" +
            "    </article>\n";
  }

  private static String createMiddleHTMLForWishlist() {
    return
        "      <form method=\"POST\" action=\"" + methodNameToCreateWishlist + "\">\n" +
            "        <input type=\"hidden\" name=\"" + nameOfUserID + "\" th:value=\"${" + nameOfUserObject + ".getEmail()}\">\n" +
            "        <button type=\"submit\" class=\"small-button åben-side-med-input\">\n" +
            "          Skab Ønskeseddel\n" +
            "        </button>\n" +
            "      </form>\n" +
            "    </div>\n" +
            "    <article class=\"grid-child\">\n" +
            "      Ingen ønskeseddel er blevet skabt her endnu.\n" +
            "    </article>\n";
  }

  private static String createEndHTMLForWishlist() {
    return """
          </div>
        """;
  }

}
