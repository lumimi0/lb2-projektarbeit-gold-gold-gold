package com.bbzbl.M347.dto;

import com.bbzbl.M347.model.Item;
import com.bbzbl.M347.model.Rarity;
import java.math.BigDecimal;

public class OpenCaseResponse {
    private Long itemId;
    private String itemName;
    private Rarity rarity;
    private BigDecimal value;
    private String imageUrl;

    public OpenCaseResponse(Item item) {
        this.itemId = item.getId();
        this.itemName = item.getName();
        this.rarity = item.getRarity();
        this.value = item.getValue();
        this.imageUrl = item.getImageUrl();
    }

    // Getters and Setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
}