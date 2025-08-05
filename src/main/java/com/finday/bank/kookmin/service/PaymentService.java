package com.finday.bank.kookmin.service;

import com.finday.bank.kookmin.dto.RequestedPaymentDTO;

public interface PaymentService {
    void payByAccount(RequestedPaymentDTO request);

    void payByCard(RequestedPaymentDTO request);
}
