package com.glady.company_service.services;

import com.glady.company_service.clients.DepositClient;
import com.glady.company_service.exceptions.ExceededAmountExeption;
import com.glady.company_service.models.Deposit;
import com.glady.company_service.repositories.CompanyRepository;
import com.glady.company_service.requests.DepositRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository repository;
    private final DepositClient client;

    public CompanyService(CompanyRepository repository, DepositClient client) {
        this.repository = repository;
        this.client = client;
    }

    /**
     * Checks if an amount is authorized before a company makes a deposit
     * @param request
     */
    public void makeDeposit(DepositRequest request) {
        String companyId = request.senderId();
        List<Deposit> deposits = request.deposits();
        BigDecimal companyBalance = repository.findBalanceByCompanyId(companyId);
        BigDecimal totalDepositAmount = calculateTotalDepositAmount(deposits.toArray(deposits.toArray(new Deposit[0])));
        if(companyBalance.compareTo(totalDepositAmount) < 0){
            throw new ExceededAmountExeption("The authorized amount is exceeded.");
        }
        else{
            client.makeDeposit(request);
        }
    }

    BigDecimal calculateTotalDepositAmount(Deposit... deposits) {
        return Arrays.stream(deposits)
                .map(deposit -> deposit.amount())
                .reduce(BigDecimal::add)
                .get();
    }
}
