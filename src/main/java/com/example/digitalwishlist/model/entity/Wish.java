package com.example.digitalwishlist.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "wishes")
public class Wish {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  /*  @Column(unique = true)*/
  private long id;
  @ManyToOne
  @JoinColumn(name = "id_WL")
  private Wishlist wishlist;
  @NotBlank(message = "Title is mandatory")
  private String title;
  private String descr;
  private double price;
  private String link;

  public Wish(String title, String descr, double price, String link) {
    this.title = title;
    this.descr = descr;
    this.price = price;
    this.link = link;
  }

  public Wish() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public void setTitle(String title) {
    this.title = title;
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

  @Override
  public String toString() {
    return "Wish{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", descr='" + descr + '\'' +
        ", price=" + price +
        ", link='" + link + '\'' +
        '}';
  }
}
