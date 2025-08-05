package com.finday.bank.kookmin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account_info")
@Data
public class AccountInfo {

    @Id
    @Column(name = "account_no")
    private Integer accountNo;

    @Column(name = "account_name", nullable = false)
    private String accountName;
}