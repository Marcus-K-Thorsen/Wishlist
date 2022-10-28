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
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_WL", referencedColumnName = "id")
  private Wishlist wishlist;
  private String titel;
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
        ", titel='" + titel + '\'' +
        ", descr='" + descr + '\'' +
        ", price=" + price +
        ", link='" + link + '\'' +
        '}';
  }
}
