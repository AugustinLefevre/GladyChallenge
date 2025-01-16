package com.glady.deposit_service.services;

import com.glady.deposit_service.models.*;
import com.glady.deposit_service.repositories.GiftRepository;
import com.glady.deposit_service.repositories.MealRepository;
import com.glady.deposit_service.requests.DepositRequest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class DepositServiceTest {
    private final DepositService service;
    private final GiftRepository giftRepository;
    private final MealRepository mealRepository;
    private final CouponFactory couponFactory;

    public DepositServiceTest() {
        giftRepository = Mockito.mock(GiftRepository.class);
        mealRepository = Mockito.mock(MealRepository.class);
        couponFactory = Mockito.mock(CouponFactory.class);
        service = new DepositService(mealRepository, giftRepository, couponFactory);
    }
    @Test
    public void testCreateCoupon(){
        //GIVEN
        Deposit deposit1 = new Deposit(new BigDecimal(100), DepositType.GIFT);
        Deposit deposit2 = new Deposit(new BigDecimal(100), DepositType.MEAL);
        DepositRequest request = new DepositRequest("userId", "senderId", Arrays.asList(deposit1, deposit2));

        //WHEN
        service.createCoupons(request);

        //THEN
        Mockito.verify(couponFactory, Mockito.times(2)).createCoupon(Mockito.eq(request.userId()), Mockito.eq(request.senderId()), Mockito.any(), Mockito.any(LocalDate.class));
        Mockito.verify(couponFactory, Mockito.times(1)).createCoupon(request.userId(), request.senderId(), deposit1, LocalDate.now());
        Mockito.verify(couponFactory, Mockito.times(1)).createCoupon(request.userId(), request.senderId(), deposit2, LocalDate.now());
        Mockito.verify(giftRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(mealRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testGetUserBalance(){
        //GIVEN
        String userId = "userId";
        Mockito.when(giftRepository.getBalanceByUserId(userId)).thenReturn(new BigDecimal(100));
        Mockito.when(mealRepository.getBalanceByUserId(userId)).thenReturn(new BigDecimal(200));

        //WHEN
        BigDecimal actual = service.getUserBalance(userId);

        //THEN
        Mockito.verify(giftRepository, Mockito.times(1)).getBalanceByUserId(userId);
        Mockito.verify(mealRepository, Mockito.times(1)).getBalanceByUserId(userId);
        Assert.assertEquals(actual, new BigDecimal(300));
    }
}
