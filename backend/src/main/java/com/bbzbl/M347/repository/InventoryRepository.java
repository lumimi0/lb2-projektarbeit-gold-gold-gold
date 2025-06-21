package com.bbzbl.M347.repository;

import com.bbzbl.M347.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByUserIdOrderByObtainedAtDesc(Long userId);

    @Query("SELECT SUM(i.item.value) FROM Inventory i WHERE i.userId = :userId")
    BigDecimal getTotalInventoryValue(Long userId);
}