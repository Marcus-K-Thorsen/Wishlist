package com.example.digitalwishlist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlists")
public class Wishlist implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true)
  private long id;
  @ManyToOne
  @JoinColumn(name = "email")
  private User user;
  @NotBlank(message = "Title is mandatory")
  private String title;

  private String descr;
  @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Wish> wishes = new ArrayList<>();

  public Wishlist(String title, String descr) {
    this.title = title;
    this.descr = descr;
  }

  public Wishlist() {
  }

  public long getId() {
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }

  public List<Wish> getWishes() {
    return wishes;
  }

/*  public void setWishes(List<Wish> wishes) {
    this.wishes = wishes;
  }*/

  @Override
  public String toString() {
    return "Wishlist{" +
        "id=" + id +
        ", user=" + user +
        ", title='" + title + '\'' +
        ", descr='" + descr + '\'' +
        ", wishes=" + wishes +
        '}';
  }
}
