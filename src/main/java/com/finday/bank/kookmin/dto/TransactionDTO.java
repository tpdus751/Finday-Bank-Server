package com.finday.bank.kookmin.dto;

import java.time.LocalDateTime;

public interface TransactionDTO {
    String getTransactionName();
    String getTransactionType();
    String getTransactionCategory();
    String getFromAccountDisplay();
    String getToAccountDisplay();
    Long getAmount();
    LocalDateTime getCreatedAt();
    String getAccountName();
    String getBankName();
    String getCardNumber();
    String getPaidCardName();
}
