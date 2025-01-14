package com.glady.deposit_service.repositories;

import com.glady.deposit_service.models.Gift;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;


@DataJpaTest
public class GiftRepositoryTest {
    @Autowired
    private GiftRepository repository;

    @Test
    public void testGetBalanceByUserId(){
        //GIVEN
        Gift gift1 = Gift.builder()
                .userId("userId")
                .senderId("senderId")
                .amount(new BigDecimal(100))
                .remainingAmount(new BigDecimal(100))
                .sendingDate(LocalDate.now())
                .expirationDate(LocalDate.now())
                .build();
        repository.save(gift1);

        Gift gift2 = Gift.builder()
                .userId("userId")
                .senderId("senderId")
                .amount(new BigDecimal(100.25))
                .remainingAmount(new BigDecimal(100.25))
                .sendingDate(LocalDate.now())
                .expirationDate(LocalDate.now())
                .build();

        repository.save(gift2);

        Gift gift3 = Gift.builder()
                .userId("userId")
                .senderId("senderId")
                .amount(new BigDecimal(100.25))
                .remainingAmount(new BigDecimal(100.25))
                .sendingDate(LocalDate.now())
                .expirationDate(LocalDate.now().minusDays(1))
                .build();

        repository.save(gift3);

        //WHEN
        BigDecimal actual = repository.getBalanceByUserId("userId");

        //THEN
        Assert.assertEquals(actual, new BigDecimal(200.25));
    }

}
