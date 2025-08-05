package com.finday.bank.kookmin.service;

import com.finday.bank.kookmin.dto.CardDTO;
import com.finday.bank.kookmin.entity.UserCard;

import java.util.List;
import java.util.Optional;

public interface CardService {
    List<CardDTO> getCardsByUserHash(String userSpecificNo);

    Optional<UserCard> getCardByCardNumber(String methodId);
}
