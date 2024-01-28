package org.payments.repository;

import org.leantech.person.dto.ProfileType;
import org.payments.entity.PaymentMethod;
import org.payments.entity.PaymentMethodDefinitions;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentMethodRepository extends R2dbcRepository<PaymentMethod, Long> {
  Flux<PaymentMethod> findAllByProfileType(ProfileType profileType);
}
