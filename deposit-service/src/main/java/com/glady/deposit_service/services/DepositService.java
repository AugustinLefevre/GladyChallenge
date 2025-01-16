package com.glady.deposit_service.services;

import com.glady.deposit_service.models.*;
import com.glady.deposit_service.repositories.GiftRepository;
import com.glady.deposit_service.repositories.MealRepository;
import com.glady.deposit_service.requests.DepositRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DepositService {
    private final MealRepository mealRepository;
    private final GiftRepository giftRepository;
    private final CouponFactory couponFactory;

    public DepositService(MealRepository mealRepository, GiftRepository giftRepository, CouponFactory couponFactory) {
        this.mealRepository = mealRepository;
        this.giftRepository = giftRepository;
        this.couponFactory = couponFactory;
    }

    /**
     * Add new coupons for user
     * @param request
     */
    public void createCoupons(DepositRequest request){
        String userId = request.userId();
        String senderId = request.senderId();
        LocalDate receiveDate = LocalDate.now();
        for(Deposit deposit : request.deposits()){
            Coupon coupon = couponFactory.createCoupon(userId, senderId, deposit, receiveDate);
            if(deposit.depositType().equals(DepositType.GIFT)){
                giftRepository.save((Gift)coupon);
            }
            else{
                mealRepository.save((Meal) coupon);
            }
        }
    }

    /**
     * Calculate the total balance available for a user
     * @param userId The user id for whom the balance is requested
     * @return The total amount of gifts and meals available
     */
    public BigDecimal getUserBalance(String userId) {
        BigDecimal giftBalance = giftRepository.getBalanceByUserId(userId);
        BigDecimal mealBalance = mealRepository.getBalanceByUserId(userId);
        return giftBalance.add(mealBalance);
    }
}


