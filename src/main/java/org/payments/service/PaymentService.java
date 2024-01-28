package org.payments.service;

import org.leantech.common.dto.TransactionResponse;
import org.payments.dto.PaymentDto;

import reactor.core.publisher.Mono;

public interface PaymentService {
  Mono<TransactionResponse> payment(PaymentDto paymentDto);
}
