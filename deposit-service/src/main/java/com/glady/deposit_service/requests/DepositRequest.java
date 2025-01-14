package com.glady.deposit_service.requests;

import com.glady.deposit_service.models.Deposit;

import java.util.List;

public record DepositRequest (
        String userId,
        String senderId,
        List<Deposit> deposits
) {
}
