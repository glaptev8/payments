package org.payments.repository;

import org.payments.entity.PaymentMethodDefinitions;
import org.payments.entity.PaymentProvider;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PaymentProviderRepository extends R2dbcRepository<PaymentProvider, Long> {
}
