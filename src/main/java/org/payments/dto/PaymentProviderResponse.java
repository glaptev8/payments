package org.payments.dto;

import org.leantech.common.dto.TransactionStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProviderResponse {
  private Long id;
  private String type;
  private String transactionStatusType;
  private String message;
}
