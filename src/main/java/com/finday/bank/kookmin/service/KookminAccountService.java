package com.finday.bank.kookmin.service;

import com.finday.bank.kookmin.dto.AccountDTO;
import com.finday.bank.kookmin.dto.TransactionCategory;
import com.finday.bank.kookmin.dto.TransactionType;
import com.finday.bank.kookmin.entity.TransactionEntity;
import com.finday.bank.kookmin.entity.UserAccount;
import com.finday.bank.kookmin.repository.TransactionRepository;
import com.finday.bank.kookmin.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KookminAccountService implements AccountService {

    private final UserAccountRepository userAccountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public List<AccountDTO> getAccountsByUserHash(String userSpecificNo) {
        return userAccountRepository.findByUserSpecificNoWithAccountName(userSpecificNo).stream()
                .map(account -> new AccountDTO(
                        account.getAccountNumber(),
                        account.getAlias(),
                        account.getBalance(),
                        account.getCreatedAt().toString(),
                        "국민은행",
                        account.getAccountInfo().getAccountName()
                ))
                .toList();
    }

    @Override
    public void withdraw(String accountNumber, Long amount, String transactionName, String toAccountDisplay) {
        UserAccount account = userAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("출금 계좌 없음"));

        if (account.getBalance() < amount) throw new RuntimeException("잔액 부족");

        account.setBalance(account.getBalance() - amount);
        userAccountRepository.save(account);

        transactionRepository.save(TransactionEntity.builder()
                .userAccountNo(account)
                .transactionType(TransactionType.출금)
                .transactionName(transactionName)
                .toAccountDisplay(toAccountDisplay)
                .amount((long) amount)
                .transactionCategory(TransactionCategory.이체)
                .build());
    }

    @Override
    public void deposit(String accountNumber, Long amount, String transactionName, String fromAccountDisplay) {
        UserAccount account = userAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("입금 계좌 없음"));

        account.setBalance(account.getBalance() + amount);
        userAccountRepository.save(account);

        transactionRepository.save(TransactionEntity.builder()
                .userAccountNo(account)
                .transactionType(TransactionType.입금)
                .transactionName(transactionName)
                .fromAccountDisplay(fromAccountDisplay)
                .amount(amount)
                .transactionCategory(TransactionCategory.이체)
                .build());
    }
}
