package com.bbzbl.M347.controller;

import com.bbzbl.M347.model.Case;
import com.bbzbl.M347.model.Item;
import com.bbzbl.M347.dto.OpenCaseResponse;
import com.bbzbl.M347.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cases")
@CrossOrigin(origins = "*")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @GetMapping
    public ResponseEntity<List<Case>> getAllCases() {
        List<Case> cases = caseService.getAllCases();
        return ResponseEntity.ok(cases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Case> getCaseById(@PathVariable Long id) {
        try {
            Case caseEntity = caseService.getCaseById(id);
            return ResponseEntity.ok(caseEntity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/open")
    public ResponseEntity<OpenCaseResponse> openCase(@PathVariable Long id) {
        try {
            Item wonItem = caseService.openCase(id);
            OpenCaseResponse response = new OpenCaseResponse(wonItem);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}