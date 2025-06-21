package com.bbzbl.M347.service;

import com.bbzbl.M347.model.Inventory;
import com.bbzbl.M347.dto.InventoryItem;
import com.bbzbl.M347.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    private static final Long DEFAULT_USER_ID = 1L;

    public List<InventoryItem> getUserInventory() {
        List<Inventory> inventoryEntries = inventoryRepository
                .findByUserIdOrderByObtainedAtDesc(DEFAULT_USER_ID);

        return inventoryEntries.stream()
                .map(InventoryItem::new)
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalInventoryValue() {
        BigDecimal totalValue = inventoryRepository.getTotalInventoryValue(DEFAULT_USER_ID);
        return totalValue != null ? totalValue : BigDecimal.ZERO;
    }

    public long getInventoryItemCount() {
        return inventoryRepository.findByUserIdOrderByObtainedAtDesc(DEFAULT_USER_ID).size();
    }
}