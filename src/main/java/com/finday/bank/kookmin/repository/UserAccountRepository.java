package com.finday.bank.kookmin.repository;

import com.finday.bank.kookmin.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    // ✅ accountInfo 조인 포함
    @Query("SELECT ua FROM UserAccount ua LEFT JOIN FETCH ua.accountInfo WHERE ua.userSpecificNo = :userSpecificNo")
    List<UserAccount> findByUserSpecificNoWithAccountName(@Param("userSpecificNo") String userSpecificNo);

    Optional<UserAccount> findByAccountNumber(String accountNumber);

    Optional<UserAccount> findByUserSpecificNoAndCardNo(String userSpecificNo, int cardNo);
}
