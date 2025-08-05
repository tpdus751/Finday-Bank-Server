package com.finday.bank.kookmin.service;

import com.finday.bank.kookmin.dto.TransactionDTO;
import com.finday.bank.kookmin.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KookminTransactionService implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> getTransactionByLatest30Days(String userSpecificNo) {
        return transactionRepository.findLatest30DaysNative(userSpecificNo);
    }

    @Override
    public List<TransactionDTO> getTransactionBySelectedMonth(String userSpecificNo, String period) {
        return transactionRepository.findByMonthNative(userSpecificNo, period);
    }
}
