package com.thelma.datawarehouse.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
public class DealRequestDto {
    @Pattern(regexp="^[A-Z]{3}$",message = "Invalid Input")
    private String orderingCurrency;

    @Pattern(regexp="^[A-Z]{3}$",message = "Invalid Input")
    private String convertedCurrency;

    @NotNull(message = "amount cannot be blank")
    private BigDecimal amount;

}
