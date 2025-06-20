package com.M347.Gold_Gold_Gold.service;

import com.M347.Gold_Gold_Gold.entity.GameState;
import com.M347.Gold_Gold_Gold.entity.Inventory;
import com.M347.Gold_Gold_Gold.entity.Skin;
import com.M347.Gold_Gold_Gold.repository.GameStateRepository;
import com.M347.Gold_Gold_Gold.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final GameStateRepository gameStateRepository;

    public InventoryService(InventoryRepository inventoryRepository, GameStateRepository gameStateRepository) {
        this.inventoryRepository = inventoryRepository;
        this.gameStateRepository = gameStateRepository;
    }

    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    public Inventory addSkinToInventory(Skin skin) {
        Inventory inventoryItem = new Inventory();
        inventoryItem.setSkin(skin);
        return inventoryRepository.save(inventoryItem);
    }

    public Double getTotalInventoryValue() {
        return inventoryRepository.findAll().stream()
                .mapToDouble(item -> item.getSkin().getPrice())
                .sum();
    }

    public GameState getGameState() {
        return gameStateRepository.findById(1L)
                .orElseGet(() -> {
                    GameState gameState = new GameState();
                    return gameStateRepository.save(gameState);
                });
    }

    @Transactional
    public GameState deductBalance(Double amount) {
        GameState gameState = getGameState();
        gameState.setBalance(gameState.getBalance() - amount);
        return gameStateRepository.save(gameState);
    }

    public boolean hasEnoughBalance(Double amount) {
        GameState gameState = getGameState();
        return gameState.getBalance() >= amount;
    }
}