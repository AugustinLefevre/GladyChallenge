package com.glady.company_service.controllers;

import com.glady.company_service.requests.DepositRequest;
import com.glady.company_service.services.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    public void createCoupons(@RequestBody DepositRequest request){
        service.makeDeposit(request);
    }

}
