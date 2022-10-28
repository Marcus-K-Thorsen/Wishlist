package com.example.digitalwishlist.model.wishlist;

import com.example.digitalwishlist.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "wishlists")
public class Wishlist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true)
  private int id;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "email_user", referencedColumnName = "email")
  private User user;
  private String titel;
  private String descr;

  private int amountOfWishes; //TODO: måske bør dette være en List<Wish>?


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }

  public int getAmountOfWishes() {
    return amountOfWishes;
  }

  public void setAmountOfWishes(int amountOfWishes) {
    this.amountOfWishes = amountOfWishes;
  }

  @Override
  public String toString() {
    return "Wishlist{" +
        "id=" + id +
        ", user=" + user +
        ", titel='" + titel + '\'' +
        ", descr='" + descr + '\'' +
        ", amountOfWishes=" + amountOfWishes +
        '}';
  }
}
