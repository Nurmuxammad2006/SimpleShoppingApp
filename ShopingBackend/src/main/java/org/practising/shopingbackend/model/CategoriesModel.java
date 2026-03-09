package org.practising.shopingbackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "categories")
public class CategoriesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    // GETTERS
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
