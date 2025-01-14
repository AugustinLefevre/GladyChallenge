package com.glady.deposit_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "meals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meal implements Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String MealId;
    @Column(nullable = false)
    BigDecimal amount;
    @Column(nullable = false)
    BigDecimal remainingAmount;
    @Column(nullable = false)
    String userId;
    @Column(nullable = false)
    String senderId;
    @Column(nullable = false)
    LocalDate sendingDate;
    @Column(nullable = false)
    LocalDate expirationDate;
}
