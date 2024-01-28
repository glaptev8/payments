package org.payments.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodMetaInfo {
  @Id
  private Long id;
  private Long paymentMethodId;
  private String country;
  private String requiredFields;
}

