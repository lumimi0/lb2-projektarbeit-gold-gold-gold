package com.bbzbl.M347.controller;

import com.bbzbl.M347.dto.InventoryItem;
import com.bbzbl.M347.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryItem>> getInventory() {
        List<InventoryItem> inventory = inventoryService.getUserInventory();
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/value")
    public ResponseEntity<Map<String, Object>> getInventoryValue() {
        BigDecimal totalValue = inventoryService.getTotalInventoryValue();
        long itemCount = inventoryService.getInventoryItemCount();

        Map<String, Object> response = new HashMap<>();
        response.put("totalValue", totalValue);
        response.put("itemCount", itemCount);

        return ResponseEntity.ok(response);
    }
}