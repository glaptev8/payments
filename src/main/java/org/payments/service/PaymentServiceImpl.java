package org.payments.service;

import java.util.Map;

import org.leantech.common.dto.Currency;
import org.leantech.common.dto.ProviderRequestDto;
import org.leantech.common.dto.ProviderRequestDto.CardData;
import org.leantech.common.dto.ProviderRequestDto.Customer;
import org.leantech.common.dto.TransactionResponse;
import org.leantech.webclient.client.paymentprovider.PaymentProviderClient;
import org.leantech.webclient.client.person.PersonClient;
import org.leantech.webclient.client.user.UserClient;
import org.payments.dto.PaymentDto;
import org.payments.dto.PaymentProviderResponse;
import org.payments.entity.PaymentMethod;
import org.payments.repository.PaymentMethodDefinitionsRepository;
import org.payments.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PersonClient personClient;
  private final UserClient userClient;
  private final PaymentMethodRepository paymentMethodRepository;
  private final PaymentMethodDefinitionsRepository paymentMethodDefinitionsRepository;
  private final PaymentProviderClient paymentProviderClient;

  @Override
  public Mono<PaymentProviderResponse> payment(PaymentDto paymentDto) {
    log.info("paymentdto {}", paymentDto);
    return personClient.userInfo(paymentDto.getEmail())
      .flatMap(userDto -> {
        log.info("userDto {}", userDto);
        return userClient.getWalletByUid(paymentDto.getWalletUid())
          .flatMap(walletDto -> {
            log.info("wallet dto {}", walletDto);
            return getPaymentMethod(walletDto.getWalletType().getCurrencyCode(), userDto.getCountry())
              .flatMap(paymentMethod -> {
                var providerRequestDto = ProviderRequestDto.builder()
                  .paymentMethod(paymentMethod.getProviderMethodType())
                  .amount(paymentDto.getAmount())
                  .notificationUrl("notification_url")
                  .externalTransactionId(123L)
                  .currency(walletDto.getWalletType().getCurrencyCode())
                  .cardData(CardData.builder()
                              .cardNumber(paymentDto.getCardNumber())
                              .cvv(paymentDto.getCvv())
                              .expDate(paymentDto.getExpDate())
                              .build())
                  .customer(Customer.builder()
                              .firstName(userDto.getUsername())
                              .lastName(userDto.getUsername())
                              .country(userDto.getCountry())
                              .build())
                  .language(userDto.getLanguage())
                  .build();
                log.info("providerRequestDto {}", providerRequestDto);
                if (paymentDto.getPaymentType().equals("TOP_UP"))
                  return paymentProviderClient.transaction(providerRequestDto)
                    .map(transactionResponse -> PaymentProviderResponse.builder()
                      .id(transactionResponse.getTransactionId())
                      .type("TOP_UP")
                      .message(transactionResponse.getMessage())
                      .transactionStatusType(transactionResponse.getStatus().toString())
                      .build());
                else if (paymentDto.getPaymentType().equals("PAY_OUT")) {
                  return paymentProviderClient.payOut(providerRequestDto)
                    .map(transactionResponse -> PaymentProviderResponse.builder()
                      .id(transactionResponse.getPayOutId())
                      .type("PAY_OUT")
                      .message(transactionResponse.getMessage())
                      .transactionStatusType(transactionResponse.getStatus().toString())
                      .build());
                }
                return Mono.empty();
              });
          });
      });
  }

  private Mono<PaymentMethod> getPaymentMethod(Currency currency, String alpha3) {
    return paymentMethodDefinitionsRepository.findByCountryAlpha3CodeAndCurrencyCode(alpha3, currency.toString())
      .flatMap(paymentMethodDefinitions -> paymentMethodRepository.findById(paymentMethodDefinitions.getPaymentMethodId()));
  }
}
