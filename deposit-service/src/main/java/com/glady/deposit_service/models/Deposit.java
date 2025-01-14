package com.glady.deposit_service.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Deposit(
        BigDecimal amount,
        DepositType depositType,
        LocalDate sendingDate
) {
}
