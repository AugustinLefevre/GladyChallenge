package com.glady.company_service.repositories;

import com.glady.company_service.models.Company;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
public class CompanyRepositoryTest {
    @Autowired
    CompanyRepository repository;

    @Test
    public void testFindBalanceById(){
        //GIVEN
        Company company = new Company();
        company.setBalance(new BigDecimal(1000));
        repository.save(company);

        //WHEN
        BigDecimal actual = repository.findBalanceByCompanyId(company.getCompanyId());

        //THEN
        BigDecimal expected = new BigDecimal(1000.00f);
        Assert.assertTrue(actual.compareTo(expected) == 0);
    }


}
