package com.glady.deposit_service.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponFactoryTest {

    private final CouponFactory factory;

    public CouponFactoryTest() {
        factory = new CouponFactory();
    }

    @Test
    public void givenMealDepositWithNonLeapYearWhenCreateCoupon(){
        //GIVEN
        String userId = "userId";
        String senderId = "senderId";
        LocalDate sendingDate = LocalDate.of(2025, 1, 1);
        Deposit deposit = new Deposit(new BigDecimal(100), DepositType.MEAL, sendingDate);

        //WHEN
        Coupon actual = factory.createCoupon(userId, senderId, deposit);

        //THEN
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual instanceof Meal);
        LocalDate expected = LocalDate.of(2025, 2, 28);
        Assert.assertEquals(expected, ((Meal) actual).getExpirationDate());
    }
    @Test
    public void givenMealDepositWithLeapYearWhenCreateCoupon(){
        //GIVEN
        String userId = "userId";
        String senderId = "senderId";
        LocalDate sendingDate = LocalDate.of(2024, 1, 1);
        Deposit deposit = new Deposit(new BigDecimal(100), DepositType.MEAL, sendingDate);

        //WHEN
        Coupon actual = factory.createCoupon(userId, senderId, deposit);

        //THEN
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual instanceof Meal);
        LocalDate expected = LocalDate.of(2024, 2, 29);
        Assert.assertEquals(expected, ((Meal) actual).getExpirationDate());
    }

    @Test
    public void givenMealDepositWithSendingDateAfterFebruaryWhenCreateCoupon(){
        //GIVEN
        String userId = "userId";
        String senderId = "senderId";
        LocalDate sendingDate = LocalDate.of(2024, 3, 1);
        Deposit deposit = new Deposit(new BigDecimal(100), DepositType.MEAL, sendingDate);

        //WHEN
        Coupon actual = factory.createCoupon(userId, senderId, deposit);

        //THEN
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual instanceof Meal);
        LocalDate expected = LocalDate.of(2025, 2, 28);
        Assert.assertEquals(expected, ((Meal) actual).getExpirationDate());
    }
    @Test
    public void givenGiftDepositWithNonLeapYearWhenCreateCoupon(){
        //GIVEN
        String userId = "userId";
        String senderId = "senderId";
        LocalDate sendingDate = LocalDate.of(2025, 1, 1);
        Deposit deposit = new Deposit(new BigDecimal(100), DepositType.GIFT, sendingDate);

        //WHEN
        Coupon actual = factory.createCoupon(userId, senderId, deposit);

        //THEN
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual instanceof Gift);
        LocalDate expected = LocalDate.of(2026, 1, 1);
        Assert.assertEquals(expected, ((Gift) actual).getExpirationDate());
    }
    @Test
    public void givenGiftDepositWithLeapYearWhenCreateCoupon(){
        //GIVEN
        String userId = "userId";
        String senderId = "senderId";
        LocalDate sendingDate = LocalDate.of(2024, 1, 1);
        Deposit deposit = new Deposit(new BigDecimal(100), DepositType.GIFT, sendingDate);

        //WHEN
        Coupon actual = factory.createCoupon(userId, senderId, deposit);

        //THEN
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual instanceof Gift);
        LocalDate expected = LocalDate.of(2024, 12, 31);
        Assert.assertEquals(expected, ((Gift) actual).getExpirationDate());
    }
}
