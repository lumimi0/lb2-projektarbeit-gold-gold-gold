package com.M347.Gold_Gold_Gold.service;

import com.M347.Gold_Gold_Gold.entity.Skin;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CaseOpeningService {

    private final Random random = new Random();

    private final String[] weapons = {"AK-47", "M4A4", "AWP", "Glock-18", "Desert Eagle", "USP-S", "Knife"};
    private final String[] skinNames = {"Redline", "Asiimov", "Hyper Beast", "Dragon Lore", "Fade", "Tiger Tooth", "Doppler", "Crimson Web"};

    public Skin generateRandomSkin(Long caseId) {
        Skin skin = new Skin();

        Skin.Rarity rarity = getWeightedRarity();

        String weapon = weapons[random.nextInt(weapons.length)];
        String skinName = skinNames[random.nextInt(skinNames.length)];

        skin.setName(weapon + " | " + skinName);
        skin.setRarity(rarity);
        skin.setPrice(calculatePriceByRarity(rarity));
        skin.setImageUrl(generateImageUrl(weapon));
        skin.setCaseId(caseId);

        return skin;
    }

    private Skin.Rarity getWeightedRarity() {
        // Gewichtete Wahrscheinlichkeiten (in Prozent)
        double rand = random.nextDouble() * 100;

        if (rand <= 55) return Skin.Rarity.CONSUMER;
        if (rand <= 80) return Skin.Rarity.INDUSTRIAL;
        if (rand <= 92) return Skin.Rarity.MIL_SPEC;
        if (rand <= 97) return Skin.Rarity.RESTRICTED;
        if (rand <= 99) return Skin.Rarity.CLASSIFIED;
        if (rand <= 99.8) return Skin.Rarity.COVERT;
        return Skin.Rarity.EXCEEDINGLY_RARE;
    }

    private Double calculatePriceByRarity(Skin.Rarity rarity) {
        double basePrice = switch (rarity) {
            case CONSUMER -> 0.03;
            case INDUSTRIAL -> 0.10;
            case MIL_SPEC -> 0.50;
            case RESTRICTED -> 2.00;
            case CLASSIFIED -> 8.00;
            case COVERT -> 25.00;
            case EXCEEDINGLY_RARE -> 150.00;
        };

        // Preisvariation (+/- 50% vom Grundpreis)
        double variation = (random.nextDouble() - 0.5) * basePrice;
        return Math.round((basePrice + variation) * 100.0) / 100.0;
    }

    private String generateImageUrl(String weapon) {
        String cleanWeapon = weapon.replace("-", "").replace(" ", "");
        return "https://via.placeholder.com/200x150/FF6B6B/FFFFFF?text=" + cleanWeapon;
    }
}