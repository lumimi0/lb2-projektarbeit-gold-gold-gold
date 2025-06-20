package com.M347.Gold_Gold_Gold.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "skins")
@Data
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    private Double price;
    private String imageUrl;
    private Long caseId;

    public enum Rarity {
        CONSUMER("Consumer"),
        INDUSTRIAL("Industrial"),
        MIL_SPEC("Mil-Spec"),
        RESTRICTED("Restricted"),
        CLASSIFIED("Classified"),
        COVERT("Covert"),
        EXCEEDINGLY_RARE("Exceedingly Rare");

        private final String displayName;

        Rarity(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}