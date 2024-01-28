package org.payments.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.leantech.common.dto.Currency;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDefinitions {
  @Id
  private Long id;
  private Long paymentMethodId;
  private Currency currencyCode;
  private String countryAlpha3Code;
  private Boolean isAllCurrencies;
  private Boolean isAllCountries;
  private Boolean isPriority;
  private Boolean isActive;
}

