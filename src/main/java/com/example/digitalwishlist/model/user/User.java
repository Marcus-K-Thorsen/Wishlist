package com.example.digitalwishlist.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
  private int amountOfWishlists; //TODO: måske bør dette være en List<Wishlist>?

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

  public int getAmountOfWishlists() {
    return amountOfWishlists;
  }

  public void setAmountOfWishlists(int amountOfWishlists) {
    this.amountOfWishlists = amountOfWishlists;
  }

  @Override
  public String toString() {
    return "User{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", givenName='" + givenName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", amountOfWishlists=" + amountOfWishlists +
        '}';
  }
}
