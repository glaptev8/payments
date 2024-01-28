package org.payments.repository;

import org.payments.entity.PaymentMethodDefinitions;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PaymentMethodDefinitionsRepository extends R2dbcRepository<PaymentMethodDefinitions, Long>  {
  Mono<PaymentMethodDefinitions> findByCountryAlpha3CodeAndCurrencyCode(String alpha3, String currencyCode);
}
