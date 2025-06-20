package com.M347.Gold_Gold_Gold.config;

import com.M347.Gold_Gold_Gold.entity.Case;
import com.M347.Gold_Gold_Gold.entity.GameState;
import com.M347.Gold_Gold_Gold.repository.CaseRepository;
import com.M347.Gold_Gold_Gold.repository.GameStateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CaseRepository caseRepository;
    private final GameStateRepository gameStateRepository;

    public DataLoader(CaseRepository caseRepository, GameStateRepository gameStateRepository) {
        this.caseRepository = caseRepository;
        this.gameStateRepository = gameStateRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Lade Beispiel-Cases, falls noch keine existieren
        if (caseRepository.count() == 0) {
            loadSampleCases();
        }

        // Initialisiere GameState, falls noch nicht vorhanden
        if (!gameStateRepository.existsById(1L)) {
            GameState gameState = new GameState();
            gameStateRepository.save(gameState);
        }
    }

    private void loadSampleCases() {
        Case[] cases = {
                createCase("Chroma Case", 2.50, "https://via.placeholder.com/150x150/FF6B6B/FFFFFF?text=Chroma"),
                createCase("Spectrum Case", 1.80, "https://via.placeholder.com/150x150/4ECDC4/FFFFFF?text=Spectrum"),
                createCase("Glove Case", 3.20, "https://via.placeholder.com/150x150/45B7D1/FFFFFF?text=Glove"),
                createCase("Prisma Case", 1.50, "https://via.placeholder.com/150x150/96CEB4/FFFFFF?text=Prisma"),
                createCase("Danger Zone Case", 0.80, "https://via.placeholder.com/150x150/FFEAA7/000000?text=Danger"),
                createCase("Horizon Case", 2.10, "https://via.placeholder.com/150x150/DDA0DD/FFFFFF?text=Horizon")
        };

        for (Case caseItem : cases) {
            caseRepository.save(caseItem);
        }
    }

    private Case createCase(String name, double price, String imageUrl) {
        Case caseItem = new Case();
        caseItem.setName(name);
        caseItem.setPrice(price);
        caseItem.setImageUrl(imageUrl);
        return caseItem;
    }
}