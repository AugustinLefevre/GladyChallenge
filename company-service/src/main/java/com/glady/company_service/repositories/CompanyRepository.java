package com.glady.company_service.repositories;

import com.glady.company_service.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    @Query("SELECT balance from Company WHERE companyId = ?1")
    BigDecimal findBalanceByCompanyId(String companyId);
}
