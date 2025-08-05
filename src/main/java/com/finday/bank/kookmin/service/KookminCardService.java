package com.finday.bank.kookmin.service;

import com.finday.bank.kookmin.dto.CardDTO;
import com.finday.bank.kookmin.entity.UserCard;
import com.finday.bank.kookmin.repository.UserCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KookminCardService implements CardService {

    private final UserCardRepository userCardRepository;

    @Override
    public List<CardDTO> getCardsByUserHash(String userSpecificNo) {
        return userCardRepository.findByUserSpecificNo(userSpecificNo).stream()
                .map(card -> new CardDTO(
                        card.getCardInfo().getCardName(),
                        card.getCardInfo().getCardImgUrl(),
                        card.getCardNumber(),
                        card.getCreatedAt().toString(),
                        "국민은행"
                ))
                .toList();
    }

    @Override
    public Optional<UserCard> getCardByCardNumber(String methodId) {
        return userCardRepository.findByCardNumber(methodId);
    }
}
