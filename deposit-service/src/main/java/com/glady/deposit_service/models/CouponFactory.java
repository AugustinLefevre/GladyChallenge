package com.glady.deposit_service.models;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class CouponFactory {
    /**
     * Used to build Gift and Meal from a DepositRequest.
     * Must be replaced by a builder if number of parameters exceeds four
     * @param userId
     * @param senderId
     * @param deposit
     * @param receiveDate
     * @return
     */
    public Coupon createCoupon(String userId, String senderId, Deposit deposit, LocalDate receiveDate){
        if(deposit.depositType().equals(DepositType.GIFT)){
            return Gift.builder()
                    .userId(userId)
                    .senderId(senderId)
                    .amount(deposit.amount())
                    .remainingAmount(deposit.amount())
                    .sendingDate(receiveDate)
                    .expirationDate(calculateGiftExpirationDate(receiveDate))
                    .build();
        }else{
            return Meal.builder()
                    .userId(userId)
                    .senderId(senderId)
                    .amount(deposit.amount())
                    .remainingAmount(deposit.amount())
                    .sendingDate(receiveDate)
                    .expirationDate(calculateMealExpirationDate(receiveDate))
                    .build();
        }
    }

    /**
     * Calculate the expiration date for a meal ticket based on its distribution date.
     * The expiration date is set at the end of next February after the sending date.
     * @param receiveDate
     * @return
     */
    private LocalDate calculateMealExpirationDate(LocalDate receiveDate){
        int dayInFebruaryForTheYear = receiveDate.withMonth(Month.FEBRUARY.getValue()).lengthOfMonth();
        LocalDate result = LocalDate.of(receiveDate.getYear(), Month.FEBRUARY, dayInFebruaryForTheYear);
        if(receiveDate.isAfter(result)){
            result = LocalDate.of(
                    result.plusYears(1).getYear(),
                    Month.FEBRUARY,
                    result.plusYears(1).lengthOfMonth());
        }
        return result;
    }

    /**
     * Calculate the expiration date for a gift card based on its distribution date.
     * The expiration date is set to 365 days after the sending date.
     * @param receiveDate
     * @return
     */
    private LocalDate calculateGiftExpirationDate(LocalDate receiveDate){
        return receiveDate.plusDays(365L);
    }
}
