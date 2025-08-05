package com.finday.bank.kookmin.service;

import com.finday.bank.kookmin.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    public List<AccountDTO> getAccountsByUserHash(String userHash);

    void withdraw(String fromAccountNumber, Long amount, String userName, String senderDisplay);

    void deposit(String toAccountNumber, Long amount, String userName, String receiverDisplay);
}
