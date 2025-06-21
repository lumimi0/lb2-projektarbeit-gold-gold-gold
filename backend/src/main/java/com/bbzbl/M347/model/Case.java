package com.bbzbl.M347.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "case_items",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> possibleItems;

    // Constructors
    public Case() {}

    public Case(String name, String imageUrl, List<Item> possibleItems) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.possibleItems = possibleItems;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Item> getPossibleItems() {
        return possibleItems;
    }

    public void setPossibleItems(List<Item> possibleItems) {
        this.possibleItems = possibleItems;
    }
}