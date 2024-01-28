package org.payments.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.leantech.webclient.client.person.PersonClient;
import org.leantech.webclient.client.user.UserClient;
import org.payments.dto.PaymentMethodDto;
import org.payments.dto.RequiredFieldDto;
import org.payments.entity.PaymentMethod;
import org.payments.entity.PaymentMethodRequiredFields;
import org.payments.repository.PaymentMethodRepository;
import org.payments.repository.PaymentMethodRequiredFieldsRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentsMethodServiceImpl implements PaymentsMethodService {

  private final UserClient userClient;
  private final PaymentMethodRepository paymentMethodRepository;
  private final PaymentMethodRequiredFieldsRepository paymentMethodRequiredFieldsRepository;
  private final PersonClient personClient;

  public Flux<PaymentMethodDto> getPaymentsMethodByWalletUid(String walletUid) {
    /*
      The name of the variable can seem weird, but originally it is wallet-api,
      initially I messed up with service name and in order to be consistent still keep it here
    */
    return userClient.getWalletByUid(walletUid)
      .flatMapMany(walletDto -> personClient.getProfile(walletDto.getProfileUid().toString())
        .flatMapMany(profileDto -> paymentMethodRepository.findAllByProfileType(profileDto.getType())
          .flatMap(paymentMethod -> paymentMethodRequiredFieldsRepository.findAllByPaymentMethodId(paymentMethod.getId())
            .map(this::getRequiredFieldDto)
            .collectList()
            .map(paymentMethodRequiredFields -> {
              var paymentMethodDto = paymentMethodResponseDto(paymentMethod);
              paymentMethodDto.setRequiredFields(paymentMethodRequiredFields);
              return paymentMethodDto;
            })
          )
        )
      );
  }

  private PaymentMethodDto paymentMethodResponseDto(PaymentMethod paymentMethod) {
    return PaymentMethodDto.builder()
      .id(paymentMethod.getId())
      .imageUrl(paymentMethod.getLogo())
      .name(paymentMethod.getName())
      .requiredFields(new ArrayList<>())
      .build();
  }

  private RequiredFieldDto getRequiredFieldDto(PaymentMethodRequiredFields paymentMethodRequiredFields) {
    return RequiredFieldDto.builder()
      .name(paymentMethodRequiredFields.getName())
      .paymentType(paymentMethodRequiredFields.getPaymentType())
      .uid(paymentMethodRequiredFields.getUid())
      .paymentMethodId(paymentMethodRequiredFields.getPaymentMethodId())
      .dataType(paymentMethodRequiredFields.getDataType())
      .countryAlpha3Code(paymentMethodRequiredFields.getCountryAlpha3Code())
      .language(paymentMethodRequiredFields.getDescription())
      .placeholder(paymentMethodRequiredFields.getPlaceholder())
      .defaultValue(paymentMethodRequiredFields.getDefaultValue())
      .representationName(paymentMethodRequiredFields.getRepresentationName())
      .validationRule(paymentMethodRequiredFields.getValidationRule())
      .validationType(paymentMethodRequiredFields.getValidationType())
      .valuesOptions(Arrays.stream(paymentMethodRequiredFields.getValuesOptions().split(" ")).toList())
      .createdAt(paymentMethodRequiredFields.getCreatedAt())
      .modifiedAt(paymentMethodRequiredFields.getModifiedAt())
      .build();
  }
}
