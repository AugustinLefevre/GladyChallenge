package com.glady.deposit_service.repositories;

import com.glady.deposit_service.models.Meal;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;


@DataJpaTest
public class MealRepositoryTest {
    @Autowired
    private MealRepository repository;

    @Test
    public void testGetBalanceByUserId(){
        //GIVEN
        Meal meal1 = Meal.builder()
                .userId("userId")
                .senderId("senderId")
                .amount(new BigDecimal(100))
                .remainingAmount(new BigDecimal(100))
                .sendingDate(LocalDate.now())
                .expirationDate(LocalDate.now())
                .build();
        repository.save(meal1);

        Meal meal2 = Meal.builder()
                .userId("userId")
                .senderId("senderId")
                .amount(new BigDecimal(100.25))
                .remainingAmount(new BigDecimal(100.25))
                .sendingDate(LocalDate.now())
                .expirationDate(LocalDate.now())
                .build();

        repository.save(meal2);

        Meal meal3 = Meal.builder()
                .userId("userId")
                .senderId("senderId")
                .amount(new BigDecimal(100.25))
                .remainingAmount(new BigDecimal(100.25))
                .sendingDate(LocalDate.now())
                .expirationDate(LocalDate.now().minusDays(1))
                .build();

        repository.save(meal3);

        //WHEN
        BigDecimal actual = repository.getBalanceByUserId("userId");

        //THEN
        Assert.assertEquals(actual, new BigDecimal(200.25));
    }

}
