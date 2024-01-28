package org.payments.repository;

import java.util.UUID;

import org.payments.entity.PaymentMethodDefinitions;
import org.payments.entity.PaymentMethodRequiredFields;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;

public interface PaymentMethodRequiredFieldsRepository extends R2dbcRepository<PaymentMethodRequiredFields, UUID> {
  Flux<PaymentMethodRequiredFields> findAllByPaymentMethodId(Long id);
}
