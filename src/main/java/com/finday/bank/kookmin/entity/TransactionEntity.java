package com.finday.bank.kookmin.entity;

import com.finday.bank.kookmin.dto.TransactionCategory;
import com.finday.bank.kookmin.dto.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_no")
    private Long transactionNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_no", nullable = false)
    private UserAccount userAccountNo;  // ✅ 엔티티로 참조

    @Column(name = "transaction_name", length = 255, nullable = false)
    private String transactionName;  // 상대방 이름 (userName)

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType; // 입금 / 출금

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_category", nullable = true)
    private TransactionCategory transactionCategory; // 식비 / 마트 등

    @Column(name = "from_account_display", length = 100)
    private String fromAccountDisplay;  // 받는 사람 통장에 표시될 이름

    @Column(name = "to_account_display", length = 100)
    private String toAccountDisplay;    // 보내는 사람 통장에 표시될 이름

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "card_number", nullable = true)
    private String cardNumber;


}
