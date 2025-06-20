package com.M347.Gold_Gold_Gold.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "skin_id")
    private Skin skin;

    private LocalDateTime acquiredAt;

    @PrePersist
    public void prePersist() {
        acquiredAt = LocalDateTime.now();
    }
}