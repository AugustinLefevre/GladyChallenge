package com.glady.deposit_service.repositories;

import com.glady.deposit_service.models.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
@Repository
public interface GiftRepository extends JpaRepository<Gift, String> {
    /**
     * Get the total amount of Gifts for a user according to the expiration date
     * @param userId
     * @return
     */
    @Query("SELECT COALESCE(SUM(remainingAmount), 0) FROM Gift where userId = ?1 AND expirationDate >= CURRENT_DATE")
    BigDecimal getBalanceByUserId(String userId);
}
