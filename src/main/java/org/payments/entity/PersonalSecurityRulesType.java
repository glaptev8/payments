package org.payments.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class PersonalSecurityRulesType {
  @Id
  private Integer id;
  private LocalDateTime createdAt;
  private UUID profileUid;
  private LocalDateTime modifiedAt;
  private String ruleType;
  private String transactionType;
  private Boolean isAllTransactionType;
  private Integer paymentMethodId;
  private Integer amountTo;
  private Integer amountFrom;
  private Integer timeValue;
  private String timeDefinition;
  private Integer transactionsCount;
  private String status;
}

