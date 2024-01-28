package org.payments.controller;

import org.payments.dto.PaymentMethodDto;
import org.payments.service.PaymentsMethodService;
import org.payments.service.PaymentsMethodServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment-methods")
public class PaymentMethodsController {

  private final PaymentsMethodService paymentsMethodService;

  @GetMapping("/{walletuid}")
  public Flux<PaymentMethodDto> paymentMethodDtoFlux(@PathVariable String walletuid) {
    return paymentsMethodService.getPaymentsMethodByWalletUid(walletuid);
  }
}
