package com.glady.company_service.services;

import com.glady.company_service.clients.DepositClient;
import com.glady.company_service.exceptions.ExceededAmountExeption;
import com.glady.company_service.models.Company;
import com.glady.company_service.repositories.CompanyRepository;
import com.glady.company_service.models.Deposit;
import com.glady.company_service.requests.DepositRequest;
import com.glady.company_service.models.DepositType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertThrows;

public class CompanyServiceTest {
    private final CompanyRepository repository;
    private final CompanyService service;
    private final DepositClient client;

    public CompanyServiceTest(){
        repository = Mockito.mock(CompanyRepository.class);
        client = Mockito.mock(DepositClient.class);
        service = new CompanyService(repository, client);
    }

    @Test
    public void givenCorrectAmountWhenMakeDepositThenCallDepositClient(){
        //GIVEN
        Company company = new Company("myId", new BigDecimal(10_000));
        String userId = "userId";
        Mockito.when(repository.findBalanceByCompanyId(company.getCompanyId())).thenReturn(company.getBalance());
        Deposit giftDeposit = new Deposit(new BigDecimal(100), DepositType.GIFT);
        Deposit mealDeposit = new Deposit(new BigDecimal(200), DepositType.MEAL);
        DepositRequest depositRequest = new DepositRequest(userId, company.getCompanyId(), Arrays.asList(giftDeposit, mealDeposit));

        //WHEN
        service.makeDeposit(depositRequest);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findBalanceByCompanyId(company.getCompanyId());
        Mockito.verify(client, Mockito.times(1)).makeDeposit(Mockito.eq(depositRequest));
    }

    @Test
    public void givenIncorrectAmountWhenMakeDepositThenReturnError(){
        //GIVEN
        Company company = new Company("myId", new BigDecimal(10_000));
        String userId = "userId";
        Mockito.when(repository.findBalanceByCompanyId(company.getCompanyId())).thenReturn(company.getBalance());
        Deposit giftDeposit = new Deposit(new BigDecimal(10_000), DepositType.GIFT);
        Deposit mealDeposit = new Deposit(new BigDecimal(200), DepositType.MEAL);
        DepositRequest depositRequest = new DepositRequest(userId, company.getCompanyId(), Arrays.asList(giftDeposit, mealDeposit));

        //WHEN
        ExceededAmountExeption exception = assertThrows(ExceededAmountExeption.class, () -> {
            service.makeDeposit(depositRequest);
        });

        //THEN
        Assert.assertEquals("The authorized amount is exceeded.", exception.getMessage());
        Mockito.verify(repository, Mockito.times(1)).findBalanceByCompanyId(company.getCompanyId());
        Mockito.verify(client, Mockito.times(0)).makeDeposit(Mockito.eq(depositRequest));
    }

    @Test
    public void calculateTotalDepositAmountTest(){
        //GIVEN
        Deposit giftDeposit = new Deposit(new BigDecimal(111), DepositType.GIFT);
        Deposit mealDeposit = new Deposit(new BigDecimal(222), DepositType.MEAL);

        //WHEN
        BigDecimal actual = service.calculateTotalDepositAmount(giftDeposit, mealDeposit);

        //THEN
        BigDecimal expected = new BigDecimal(333);
        Assert.assertEquals(expected, actual);
    }
}
