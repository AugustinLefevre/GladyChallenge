package com.glady.company_service.requests;

import com.glady.company_service.models.Deposit;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


public record DepositRequest(
        String userId,
        String senderId,
        List<Deposit> deposits
) {
}
