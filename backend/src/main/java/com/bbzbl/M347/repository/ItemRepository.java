package com.bbzbl.M347.repository;

import com.bbzbl.M347.model.Item;
import com.bbzbl.M347.model.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByRarity(Rarity rarity);
}