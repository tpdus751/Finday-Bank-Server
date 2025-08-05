package com.finday.bank.kookmin.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_account")
@Data
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_no")
    private Long userAccountNo;

    @Column(name = "user_specific_no", length = 64, nullable = false)
    private String userSpecificNo;

    @Column(name = "account_no", nullable = true, insertable = false, updatable = false)
    private Integer accountNo;

    @Column(name = "account_number", length = 30, nullable = false)
    private String accountNumber;

    @Column(name = "card_no", nullable = true)
    private Integer cardNo;

    @Column(name = "balance", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long balance;

    @Column(name = "alias", length = 100)
    private String alias;

    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // ✅ account_info 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_no", referencedColumnName = "account_no")
    private AccountInfo accountInfo;
}
