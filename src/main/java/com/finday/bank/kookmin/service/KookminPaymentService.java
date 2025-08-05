package com.finday.bank.kookmin.service;

import com.finday.bank.kookmin.dto.RequestedPaymentDTO;
import com.finday.bank.kookmin.dto.TransactionCategory;
import com.finday.bank.kookmin.dto.TransactionType;
import com.finday.bank.kookmin.entity.TransactionEntity;
import com.finday.bank.kookmin.entity.UserAccount;
import com.finday.bank.kookmin.entity.UserCard;
import com.finday.bank.kookmin.repository.TransactionRepository;
import com.finday.bank.kookmin.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KookminPaymentService implements PaymentService {

    private final UserAccountRepository userAccountRepository;
    private final TransactionRepository transactionRepository;
    private final CardService cardService;

    @Override
    public void payByAccount(RequestedPaymentDTO request) {
        if (request.getUserSpecificNo() == null || request.getMethodId() == null) {
            throw new RuntimeException("사용자 고유번호 또는 결제 방식 ID가 존재하지 않습니다.");
        }

        UserAccount account = userAccountRepository.findByAccountNumber(request.getMethodId())
                .orElseThrow(() -> new RuntimeException("출금 계좌 없음"));

        if (account.getBalance() - request.getAmount() < 0) {
            throw new RuntimeException("잔액이 부족합니다.");
        }

        account.setBalance(account.getBalance() - request.getAmount());

        userAccountRepository.save(account);

        transactionRepository.save(TransactionEntity.builder()
                .userAccountNo(account)
                .transactionName(request.getPlaceOfUse())
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .transactionCategory(TransactionCategory.valueOf(request.getCategory()))
                .amount(request.getAmount())
                .build()
        );
    }

    @Override
    public void payByCard(RequestedPaymentDTO request) {
        if (request.getUserSpecificNo() == null || request.getMethodId() == null) {
            throw new RuntimeException("사용자 고유번호 또는 결제 방식 ID가 존재하지 않습니다.");
        }

        UserCard userCard = cardService.getCardByCardNumber(request.getMethodId())
                .orElseThrow(() -> new RuntimeException("출금 계좌 없음"));;

        int cardNo = userCard.getCardNo();

        UserAccount account = userAccountRepository.findByUserSpecificNoAndCardNo(request.getUserSpecificNo(), cardNo)
            .orElseThrow(() -> new RuntimeException("출금 계좌 없음"));

        if (account.getBalance() - request.getAmount() < 0) {
            throw new RuntimeException("잔액이 부족합니다.");
        }

        account.setBalance(account.getBalance() - request.getAmount());

        userAccountRepository.save(account);

        transactionRepository.save(TransactionEntity.builder()
                .userAccountNo(account)
                .transactionName(request.getPlaceOfUse())
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .transactionCategory(TransactionCategory.valueOf(request.getCategory()))
                .amount(request.getAmount())
                .cardNumber(request.getMethodId())
                .build()
        );
    }
}
