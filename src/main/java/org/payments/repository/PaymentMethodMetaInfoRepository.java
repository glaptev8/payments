package org.payments.repository;

import org.payments.entity.PaymentMethodDefinitions;
import org.payments.entity.PaymentMethodMetaInfo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PaymentMethodMetaInfoRepository extends R2dbcRepository<PaymentMethodMetaInfo, Long> {
}
