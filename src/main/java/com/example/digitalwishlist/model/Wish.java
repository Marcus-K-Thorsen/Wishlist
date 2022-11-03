package com.example.digitalwishlist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "wishes")
public class Wish {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true)
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

  public Wish(Wishlist wishlist, String title, String descr, double price, String link) {
    this.wishlist = wishlist;
    this.title = title;
    this.descr = descr;
    this.price = price;
    this.link = link;
  }

  public Wish(Wishlist wishlist, String title, String descr, String link) {
    this.wishlist = wishlist;
    this.title = title;
    this.descr = descr;
    this.link = link;
  }

  public Wish(Wishlist wishlist, String title, String descr, double price) {
    this.wishlist = wishlist;
    this.title = title;
    this.descr = descr;
    this.price = price;
  }

  public Wish(Wishlist wishlist, String title, String descr) {
    this.wishlist = wishlist;
    this.title = title;
    this.descr = descr;
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
        ", wishlist=" + wishlist +
        ", title='" + title + '\'' +
        ", descr='" + descr + '\'' +
        ", price=" + price +
        ", link='" + link + '\'' +
        '}';
  }

  // Metoder jeg har tilført for HTML kan bruge dem
  public String displayLink() {
    if (link == null || link.isBlank()) {
      return "Intet link er blevet angivet.";
    }
    return "Link til det ønskede produkt.";
  }

  public String displayDescr() {
    if (descr == null || descr.isBlank()) {
      return "Der er ikke blevet angivet en beskrivelse.";
    }
    return descr;
  }

  public String displayPrice() {
    if (price == 0) {
      return "Ingen pris er blevet angivet.";
    }
    return price + " kr.";
  }

  public boolean isThereLink () {
    if (link == null || link.isBlank()) {
      return false;
    }
    return true;
  }

  public boolean isThereNoLink () {
    return !isThereLink();
  }
}
