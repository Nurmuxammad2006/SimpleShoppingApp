package org.practising.shopingbackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "products")
public class ProductsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "price_min")
    private Double priceMin;

    @Column(name = "price_max")
    private Double priceMax;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "badge")
    private String badge;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // GETTERS
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Double getPriceMin() {
        return priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBadge() {
        return badge;
    }

    public Integer getRating() {
        return rating;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    // SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}
