package org.payments.repository;

import org.payments.entity.SecurityTriggeredRules;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;

public interface SecurityTriggeredRulesRepository extends R2dbcRepository<SecurityTriggeredRules, Integer> {
  Flux<SecurityTriggeredRules> findAllByWalletUid(String walletUid);
}
