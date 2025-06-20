package com.M347.Gold_Gold_Gold.controller;

import com.M347.Gold_Gold_Gold.entity.Case;
import com.M347.Gold_Gold_Gold.entity.Skin;
import com.M347.Gold_Gold_Gold.repository.CaseRepository;
import com.M347.Gold_Gold_Gold.repository.SkinRepository;
import com.M347.Gold_Gold_Gold.service.CaseOpeningService;
import com.M347.Gold_Gold_Gold.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cases")
@CrossOrigin(origins = "http://localhost:4200")
public class CaseController {

    private final CaseRepository caseRepository;
    private final SkinRepository skinRepository;
    private final CaseOpeningService caseOpeningService;
    private final InventoryService inventoryService;

    public CaseController(CaseRepository caseRepository, SkinRepository skinRepository,
                          CaseOpeningService caseOpeningService, InventoryService inventoryService) {
        this.caseRepository = caseRepository;
        this.skinRepository = skinRepository;
        this.caseOpeningService = caseOpeningService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    @PostMapping("/{caseId}/open")
    public ResponseEntity<?> openCase(@PathVariable Long caseId) {
        Optional<Case> caseOptional = caseRepository.findById(caseId);

        if (caseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Case caseItem = caseOptional.get();

        if (!inventoryService.hasEnoughBalance(caseItem.getPrice())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Nicht genug Geld vorhanden"));
        }

        Skin generatedSkin = caseOpeningService.generateRandomSkin(caseId);
        Skin savedSkin = skinRepository.save(generatedSkin);
        inventoryService.addSkinToInventory(savedSkin);
        inventoryService.deductBalance(caseItem.getPrice());

        return ResponseEntity.ok(savedSkin);
    }
}