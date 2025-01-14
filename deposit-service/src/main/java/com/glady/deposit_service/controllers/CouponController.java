package com.glady.deposit_service.controllers;

import com.glady.deposit_service.requests.DepositRequest;
import com.glady.deposit_service.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/deposit")
public class CouponController {
    @Autowired
    private DepositService service;

    @GetMapping("/user/{id}/balance")
    public BigDecimal getUserBalance(@PathVariable("id") String user_id){
        return service.getUserBalance(user_id);
    }

    @PostMapping
    public void createCoupons(@RequestBody DepositRequest request){
        service.createCoupons(request);
    }
}
