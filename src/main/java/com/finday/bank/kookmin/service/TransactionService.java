package com.finday.bank.kookmin.service;

import com.finday.bank.kookmin.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getTransactionByLatest30Days(String userSpecificNo);

    List<TransactionDTO> getTransactionBySelectedMonth(String userSpecificNo, String period);
}
