package com.finday.bank.kookmin.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_cards")
@Data
public class UserCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_card_no")
    private Long userCardNo;

    @Column(name = "user_specific_no", length = 64, nullable = false)
    private String userSpecificNo;

    @Column(name = "card_no", nullable = true)
    private Integer cardNo;

    @Column(name = "card_number", length = 30, nullable = false)
    private String cardNumber;

    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_no", insertable = false, updatable = false)
    private CardInfo cardInfo;
}
