package com.glady.company_service.models;

import java.math.BigDecimal;

public record Deposit(BigDecimal amount, DepositType depositType) {
}
