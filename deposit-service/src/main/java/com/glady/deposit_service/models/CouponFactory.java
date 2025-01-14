package com.glady.deposit_service.models;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CouponFactory {
    public Coupon createCoupon(String userId, String senderId, Deposit deposit){
        if(deposit.depositType().equals(DepositType.GIFT)){
            return Gift.builder()
                    .userId(userId)
                    .senderId(senderId)
                    .amount(deposit.amount())
                    .remainingAmount(deposit.amount())
                    .sendingDate(deposit.sendingDate())
                    .expirationDate(calculateGiftExpirationDate(deposit.sendingDate()))
                    .build();
        }else{
            return Meal.builder()
                    .userId(userId)
                    .senderId(senderId)
                    .amount(deposit.amount())
                    .remainingAmount(deposit.amount())
                    .sendingDate(deposit.sendingDate())
                    .expirationDate(calculateMealExpirationDate(deposit.sendingDate()))
                    .build();
        }
    }

    /**
     * Calculate the expiration date for a meal ticket based on its distribution date.
     * The expiration date is set at the end of next February after the sending date.
     * @param sendingDate
     * @return
     */
    private LocalDate calculateMealExpirationDate(LocalDate sendingDate){
        int dayInFebruaryForTheYear = sendingDate.withMonth(2).lengthOfMonth();
        LocalDate result = LocalDate.of(sendingDate.getYear(), 2, dayInFebruaryForTheYear);
        if(sendingDate.isAfter(result)){
            result = result.plusYears(1);
            result = result.withDayOfMonth(result.lengthOfMonth());
        }
        return result;
    }

    /**
     * Calculate the expiration date for a gift card based on its distribution date.
     * The expiration date is set to 365 days after the sending date.
     * @param sendingDate
     * @return
     */
    private LocalDate calculateGiftExpirationDate(LocalDate sendingDate){
        return sendingDate.plusDays(365L);
    }
}
