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
public class SecurityTriggeredRules {
  @Id
  private Integer id;
  private UUID paymentRequestUid;
  private Boolean generalRule;
  private UUID profileUid;
  private UUID walletUid;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private String ruleType;
  private Integer ruleId;
  private String status;
}

