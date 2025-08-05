package com.finday.bank.kookmin.repository;

import com.finday.bank.kookmin.entity.UserAccount;
import com.finday.bank.kookmin.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {
    List<UserCard> findByUserSpecificNo(String userSpecificNo);

    Optional<UserCard> findByCardNumber(String methodId);
}
