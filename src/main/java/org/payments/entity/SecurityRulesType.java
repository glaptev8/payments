package org.payments.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.leantech.common.dto.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class SecurityRulesType {
  @Id
  private Integer id;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private String ruleType;
  private TransactionType transactionType;
  private Boolean isAllTransactionType;
  private Long paymentMethodId;
  private Integer amountTo;
  private Integer amountFrom;
  private Integer timeValue;
  private String timeDefinition;
  private Integer transactionsCount;
  private String status;
}
