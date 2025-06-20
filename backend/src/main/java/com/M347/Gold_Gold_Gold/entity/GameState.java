package com.M347.Gold_Gold_Gold.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "game_state")
@Data
public class GameState {
    @Id
    private Long id = 1L;

    private Double balance = 100.0;
}