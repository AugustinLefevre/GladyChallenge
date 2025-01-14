package com.glady.deposit_service.repositories;

import com.glady.deposit_service.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MealRepository extends JpaRepository<Meal, String> {
    /**
     * Get the total amount of Meals for a user according to the expiration date
     * @param userId
     * @return
     */
    @Query("SELECT COALESCE(SUM(remainingAmount), 0) FROM Meal where userId = ?1 AND expirationDate >= CURRENT_DATE")
    BigDecimal getBalanceByUserId(String userId);
}
