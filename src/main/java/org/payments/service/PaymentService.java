package org.payments.service;

import org.leantech.common.dto.TransactionResponse;
import org.payments.dto.PaymentDto;
import org.payments.dto.PaymentProviderResponse;

import reactor.core.publisher.Mono;

public interface PaymentService {
  Mono<PaymentProviderResponse> payment(PaymentDto paymentDto);
}
