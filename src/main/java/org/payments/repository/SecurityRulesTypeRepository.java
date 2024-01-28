package org.payments.repository;

import org.payments.entity.SecurityRulesType;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface SecurityRulesTypeRepository extends R2dbcRepository<SecurityRulesType, Integer> {

}
