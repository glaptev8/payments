package org.payments.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentMethodDto {
  private Long id;
  private String name;
  private String providerMethodType;
  private String imageUrl;
  private List<RequiredFieldDto> requiredFields;
}
