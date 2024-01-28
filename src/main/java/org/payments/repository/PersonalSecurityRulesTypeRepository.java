package org.payments.repository;

import org.payments.entity.PaymentMethodDefinitions;
import org.payments.entity.PersonalSecurityRulesType;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PersonalSecurityRulesTypeRepository extends R2dbcRepository<PersonalSecurityRulesType, Integer> {
}
