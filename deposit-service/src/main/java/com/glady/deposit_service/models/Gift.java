package com.glady.deposit_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gifts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gift implements Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String giftId;
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
