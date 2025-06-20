package com.M347.Gold_Gold_Gold.controller;

import com.M347.Gold_Gold_Gold.entity.GameState;
import com.M347.Gold_Gold_Gold.entity.Inventory;
import com.M347.Gold_Gold_Gold.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("http://localhost:4200")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventoryItems() {
        return inventoryService.getAllInventoryItems();
    }

    @GetMapping("/value")
    public Map<String, Double> getTotalInventoryValue() {
        return Map.of("totalValue", inventoryService.getTotalInventoryValue());
    }

    @GetMapping("/balance")
    public Map<String, Double> getBalance() {
        GameState gameState = inventoryService.getGameState();
        return Map.of("balance", gameState.getBalance());
    }
}