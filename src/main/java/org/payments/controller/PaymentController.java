package org.payments.controller;

import org.leantech.common.dto.TransactionResponse;
import org.payments.dto.PaymentDto;
import org.payments.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("/payment")
  Mono<TransactionResponse> payment(@RequestBody PaymentDto paymentDto) {
      return paymentService.payment(paymentDto);
  }
}
