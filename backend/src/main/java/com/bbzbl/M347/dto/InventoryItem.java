package com.bbzbl.M347.dto;

import com.bbzbl.M347.model.Inventory;
import com.bbzbl.M347.model.Rarity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InventoryItem {
    private Long inventoryId;
    private String itemName;
    private Rarity rarity;
    private BigDecimal value;
    private String imageUrl;
    private LocalDateTime obtainedAt;

    public InventoryItem(Inventory inventory) {
        this.inventoryId = inventory.getId();
        this.itemName = inventory.getItem().getName();
        this.rarity = inventory.getItem().getRarity();
        this.value = inventory.getItem().getValue();
        this.imageUrl = inventory.getItem().getImageUrl();
        this.obtainedAt = inventory.getObtainedAt();
    }

    // Getters and Setters
    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getObtainedAt() {
        return obtainedAt;
    }

    public void setObtainedAt(LocalDateTime obtainedAt) {
        this.obtainedAt = obtainedAt;
    }
}