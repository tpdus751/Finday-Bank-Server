package com.finday.bank.kookmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    private String userName;
    private String fromBankName;
    private String fromAccountNumber;
    private String toBankName;
    private String toAccountNumber;
    private Long amount;
    private String note;
    private String senderDisplay;
    private String receiverDisplay;
    private String userSpecificNo;
}