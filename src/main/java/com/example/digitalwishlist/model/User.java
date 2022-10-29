package com.example.digitalwishlist.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @Column(unique = true)
  private String email;
  @Column(name = "pw")
  private String password;
  private String givenName;
  private String lastName;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Wishlist> wishlists = new ArrayList<>();
  private int amount = wishlists.size();

  public User(String email, String password, String givenName, String lastName) {
    this.email = email;
    this.password = password;
    this.givenName = givenName;
    this.lastName = lastName;
  }

  public User() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Wishlist> getWishlists() {
    return wishlists;
  }

  public void setWishlists(List<Wishlist> wishlists) {
    this.wishlists = wishlists;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "User{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", givenName='" + givenName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", wishlists=" + wishlists +
        ", amount=" + amount +
        '}';
  }
}
