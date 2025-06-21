package com.bbzbl.M347.service;

import com.bbzbl.M347.model.Case;
import com.bbzbl.M347.model.Item;
import com.bbzbl.M347.model.Inventory;
import com.bbzbl.M347.model.Rarity;
import com.bbzbl.M347.repository.CaseRepository;
import com.bbzbl.M347.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    private final Random random = new Random();

    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    public Case getCaseById(Long id) {
        return caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found with id: " + id));
    }

    public Item openCase(Long caseId) {
        Case caseToOpen = getCaseById(caseId);

        // Bestimme die Seltenheit basierend auf Wahrscheinlichkeiten
        Rarity selectedRarity = determineRarity();

        // Filtere Items nach Seltenheit
        List<Item> itemsOfRarity = caseToOpen.getPossibleItems().stream()
                .filter(item -> item.getRarity() == selectedRarity)
                .toList();

        // Falls keine Items der bestimmten Seltenheit vorhanden sind,
        // nimm ein zufälliges Item aus dem Case
        if (itemsOfRarity.isEmpty()) {
            itemsOfRarity = caseToOpen.getPossibleItems();
        }

        // Wähle zufälliges Item
        Item selectedItem = itemsOfRarity.get(random.nextInt(itemsOfRarity.size()));

        // Füge Item zum Inventory hinzu
        Inventory inventoryEntry = new Inventory(selectedItem);
        inventoryRepository.save(inventoryEntry);

        return selectedItem;
    }

    private Rarity determineRarity() {
        double randomValue = random.nextDouble() * 100;
        double cumulative = 0;

        for (Rarity rarity : Rarity.values()) {
            cumulative += rarity.getDropRate();
            if (randomValue <= cumulative) {
                return rarity;
            }
        }

        // Fallback - sollte nie erreicht werden
        return Rarity.GEWÖHNLICH;
    }
}