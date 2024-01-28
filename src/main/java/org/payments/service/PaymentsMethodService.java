package org.payments.service;

import org.payments.dto.PaymentMethodDto;

import reactor.core.publisher.Flux;

public interface PaymentsMethodService {
  Flux<PaymentMethodDto> getPaymentsMethodByWalletUid(String walletUid);
}
