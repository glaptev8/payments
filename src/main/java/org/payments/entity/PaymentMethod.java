package org.payments.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.leantech.person.dto.ProfileType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {

  @Id
  private Long id;
  private String type;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private String name;
  private Boolean isActive;
  private String providerUniqueId;
  private String providerMethodType;
  private String logo;
  private ProfileType profileType;
  private Integer providerId;
}

