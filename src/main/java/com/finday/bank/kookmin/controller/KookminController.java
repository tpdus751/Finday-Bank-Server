package com.finday.bank.kookmin.controller;

import com.finday.bank.kookmin.dto.*;
import com.finday.bank.kookmin.entity.UserAccount;
import com.finday.bank.kookmin.repository.UserAccountRepository;
import com.finday.bank.kookmin.service.AccountService;
import com.finday.bank.kookmin.service.CardService;
import com.finday.bank.kookmin.service.PaymentService;
import com.finday.bank.kookmin.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.TableGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kookmin")
@RequiredArgsConstructor
@TableGenerator(name = "국민은행 계좌 API")
public class KookminController {

    private final AccountService accountService;
    private final UserAccountRepository userAccountRepository;
    private final CardService cardService;
    private final PaymentService paymentService;
    private final TransactionService transactionService;

    @GetMapping("/accounts")
    @Operation(summary = "계좌 조회", description = "SHA-256 해시된 사용자 식별자로 국민은행 계좌 조회")
    public ResponseEntity<List<AccountDTO>> getAccounts(
            @RequestParam("userSpecificNo") String userSpecificNo) {
        List<AccountDTO> result = accountService.getAccountsByUserHash(userSpecificNo);
        for (AccountDTO acc : result) {
            System.out.println(acc);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/cards")
    @Operation(summary = "카드 조회", description = "SHA-256 해시된 사용자 식별자로 국민은행 카드 조회")
    public ResponseEntity<List<CardDTO>> getCards(
            @RequestParam("userSpecificNo") String userSpecificNo) {
        List<CardDTO> result = cardService.getCardsByUserHash(userSpecificNo);
        for (CardDTO card : result) {
            System.out.println(card);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(
            @RequestBody TransferRequestDTO request
    ) {
        accountService.withdraw(
                request.getFromAccountNumber(),
                request.getAmount(),
                request.getUserName(),        // 👉 거래내역 이름
                request.getSenderDisplay() // 👉 내 통장표시
        );
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(
            @RequestBody TransferRequestDTO request
    ) {
        accountService.deposit(
                request.getToAccountNumber(),
                request.getAmount(),
                request.getUserName(),        // 👉 거래내역 이름
                request.getReceiverDisplay()   // 👉 받은 사람 통장표시
        );
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/cancel-withdraw")
    public ResponseEntity<String> cancelWithdraw(@RequestBody TransferRequestDTO request) {
        try {
            UserAccount account = userAccountRepository.findByAccountNumber(request.getFromAccountNumber())
                    .orElseThrow(() -> new IllegalArgumentException("계좌를 찾을 수 없습니다."));

            // 출금 롤백 → 잔액 복구
            account.setBalance(account.getBalance() + request.getAmount());
            userAccountRepository.save(account);

            return ResponseEntity.ok("출금 취소 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("출금 취소 실패: " + e.getMessage());
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<String> requestPay(@RequestBody RequestedPaymentDTO request) {

        System.out.println("요청 들어옴!!! /pay");

        System.out.println(request.toString());

        // 계좌 결제 방식
        if (request.getMethodType().equals("account")) {
            try {
                paymentService.payByAccount(request);
                return ResponseEntity.ok("계좌 결제 완료");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("계좌 결제 실패: " + e.getMessage());
            }

        // 카드 결제 방식
        } else {
            try {
                paymentService.payByCard(request);
                return ResponseEntity.ok("카드 결제 완료");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("카드 결제 실패: " + e.getMessage());
            }
        }

    }

    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionDTO>> getTransactionByPeriod(
            @RequestParam("userSpecificNo") String userSpecificNo,
            @RequestParam("period") String period
    ) {
        if (userSpecificNo == null || period == null) {
            System.out.println("잘못된 요청입니다.");
            return ResponseEntity.ok(null);
        }

        System.out.println("트랜잭션 요청 들어옴");

        if (period.equals("30Days")) {
            List<TransactionDTO> transactionList = transactionService.getTransactionByLatest30Days(userSpecificNo);
            System.out.println(transactionList);
            return ResponseEntity.ok(transactionList);
        } else {
            List<TransactionDTO> transactionList = transactionService.getTransactionBySelectedMonth(userSpecificNo, period);
            System.out.println(transactionList);
            return ResponseEntity.ok(transactionService.getTransactionBySelectedMonth(userSpecificNo, period));
        }

    }


}