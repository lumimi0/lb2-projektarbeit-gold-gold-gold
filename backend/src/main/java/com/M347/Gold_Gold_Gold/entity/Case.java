package com.M347.Gold_Gold_Gold.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cases")
@Data
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price = 2.50;
    private String imageUrl;
}