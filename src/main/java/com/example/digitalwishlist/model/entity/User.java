package com.example.digitalwishlist.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @Column(unique = true)
  @NotBlank(message = "Email/Username is mandatory")
  private String email;
  @Column(name = "pw")
  @NotBlank(message = "Password is mandatory")
  private String password;
  @NotBlank(message = "Given name is mandatory")
  private String givenName;
  @NotBlank(message = "Last name is mandatory")
  private String lastName;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Wishlist> wishlists = new ArrayList<>();

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

  @Override
  public String toString() {
    return "User{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", givenName='" + givenName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}
