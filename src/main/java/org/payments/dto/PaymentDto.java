package org.payments.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.payments.deserializer.ExpLocalDateDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
  private String email;
  private String cardNumber;
  @JsonFormat(pattern = "MM/yy")
  @JsonDeserialize(using = ExpLocalDateDeserializer.class)
  private LocalDate expDate;
  private String cvv;
  private String walletUid;
  private BigDecimal amount;
}
