package com.bbzbl.M347.model;

public enum Rarity {
    GEWÖHNLICH("Gewöhnlich", 79.92),    // Consumer Grade - 79.92%
    SELTEN("Selten", 15.98),            // Industrial Grade - 15.98%
    EPISCH("Episch", 3.2),              // Mil-Spec - 3.2%
    LEGENDÄR("Legendär", 0.9);          // Restricted/Classified/Covert - 0.9%

    private final String displayName;
    private final double dropRate;

    Rarity(String displayName, double dropRate) {
        this.displayName = displayName;
        this.dropRate = dropRate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getDropRate() {
        return dropRate;
    }
}