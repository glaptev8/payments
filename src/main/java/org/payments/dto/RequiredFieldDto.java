package org.payments.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.leantech.common.dto.PaymentMethod;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequiredFieldDto {
  private String description;
  private String placeholder;
  private String representationName;
  private String language;
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
  private String paymentMethod;
  private List<String> valuesOptions;
}
