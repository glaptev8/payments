package org.payments.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.leantech.common.dto.PaymentMethod;
import org.leantech.person.dto.DataType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodRequiredFields {
  @Id
  private UUID uid;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Long paymentMethodId;
  private PaymentMethod paymentType;
  private String countryAlpha3Code;
  private String name;
  private String dataType;
  private String validationType;
  private String validationRule;
  private String defaultValue;
  private String valuesOptions;
  private String description;
  private String placeholder;
  private String representationName;
  private String language;
  private Boolean isActive;
}

