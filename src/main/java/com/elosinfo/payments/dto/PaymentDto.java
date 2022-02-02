package com.elosinfo.payments.dto;

import com.elosinfo.payments.entity.PaymentType;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentDto {
    private Long id;

    @NotNull
    private Long customerId;

    @NotBlank(message = "Last name required")
    @Length(min = 3, max = 20, message = "Last Name must be between 3 and 20 characters long")
    private String description;

    @NotNull
    @DecimalMin(value = "0.01")
    @Digits(integer = 12, fraction = 2)
    private BigDecimal totalAmount;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = false)
    @Digits(integer = 12, fraction = 2)
    private BigDecimal totalPaid;

    @FutureOrPresent(message = "Start date must be a future data")
    private LocalDateTime startDate;

    @FutureOrPresent(message = "End date must be a future data")
    private LocalDateTime endDate;

    @NotNull
    @Min(value = 1, message = "Installments number must be greater than or equal to 1")
    private Integer installmentsNumber;

    @NotNull
    private Boolean active;

    @NotNull
    private PaymentType paymentType;
}
