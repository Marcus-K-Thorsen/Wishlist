package com.example.digitalwishlist.model;

public class DTO {


  private String wishlistId;


  public DTO(String wishlistId) {
    this.wishlistId = wishlistId;

  }

  public DTO() {
  }

  public String getWishlistId() {
    return wishlistId;
  }

  public void setWishlistId(String wishlistId) {
    this.wishlistId = wishlistId;
  }
}
