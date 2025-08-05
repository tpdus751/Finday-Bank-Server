package com.finday.bank.kookmin.repository;

import com.finday.bank.kookmin.dto.TransactionDTO;
import com.finday.bank.kookmin.entity.TransactionEntity;
import com.finday.bank.kookmin.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = """
        SELECT 
            t.transaction_name AS transactionName,
            t.transaction_type AS transactionType,
            t.transaction_category AS transactionCategory,
            t.from_account_display AS fromAccountDisplay,
            t.to_account_display AS toAccountDisplay,
            t.amount AS amount,
            t.created_at AS createdAt,
            ai.account_name AS accountName,
            '국민은행' AS bankName,
            t.card_number AS cardNumber,
            ci.card_name AS paidCardName
        FROM transaction t
        JOIN user_account ua ON t.user_account_no = ua.user_account_no
        JOIN account_info ai ON ua.account_no = ai.account_no
        LEFT JOIN user_cards uc ON t.card_number = uc.card_number
        LEFT JOIN card_info ci ON uc.card_no = ci.card_no
        WHERE ua.user_specific_no = :userSpecificNo
          AND t.created_at >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
        ORDER BY t.created_at DESC
        """, nativeQuery = true)
    List<TransactionDTO> findLatest30DaysNative(@Param("userSpecificNo") String userSpecificNo);


    @Query(value = """
        SELECT 
            t.transaction_name AS transactionName,
            t.transaction_type AS transactionType,
            t.transaction_category AS transactionCategory,
            t.from_account_display AS fromAccountDisplay,
            t.to_account_display AS toAccountDisplay,
            t.amount AS amount,
            t.created_at AS createdAt,
            ai.account_name AS accountName,
            '국민은행' AS bankName,
            t.card_number AS cardNumber,
            ci.card_name AS paidCardName
        FROM transaction t
        JOIN user_account ua ON t.user_account_no = ua.user_account_no
        JOIN account_info ai ON ua.account_no = ai.account_no
        LEFT JOIN user_cards uc ON t.card_number = uc.card_number
        LEFT JOIN card_info ci ON uc.card_no = ci.card_no
        WHERE ua.user_specific_no = :userSpecificNo
          AND DATE_FORMAT(t.created_at, '%Y-%m') = :period
        ORDER BY t.created_at DESC
        """, nativeQuery = true)
    List<TransactionDTO> findByMonthNative(@Param("userSpecificNo") String userSpecificNo,
                                           @Param("period") String period);

}