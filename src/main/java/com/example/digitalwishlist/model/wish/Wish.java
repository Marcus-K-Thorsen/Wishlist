package com.example.digitalwishlist.model.wish;

import com.example.digitalwishlist.model.wishlist.Wishlist;

import javax.persistence.*;

@Entity
@Table(name = "wishes")
public class Wish {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true)
  private int id;
  @ManyToOne
  @JoinColumn(name = "id_WL")
  private Wishlist wishlist;
  private String title;
  private String descr;
  private double price;
  private String link;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Wishlist getWishlist() {
    return wishlist;
  }

  public void setWishlist(Wishlist wishlist) {
    this.wishlist = wishlist;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String titel) {
    this.title = titel;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
