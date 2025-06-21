package com.bbzbl.M347.service;

import com.bbzbl.M347.model.Item;
import com.bbzbl.M347.model.Rarity;
import com.bbzbl.M347.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public List<Item> getItemsByRarity(Rarity rarity) {
        return itemRepository.findByRarity(rarity);
    }
}