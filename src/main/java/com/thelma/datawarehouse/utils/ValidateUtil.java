package com.thelma.datawarehouse.utils;


import com.thelma.datawarehouse.exception.InvalidCurrencyException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;


@RequiredArgsConstructor
@Component
@Getter
public class ValidateUtil {


    public String validateCurrency(String code) {
        for(Currency currency : Currency.getAvailableCurrencies()) {
            if(currency.getCurrencyCode().equals(code)) {
                return code;

            }
        }
        throw new InvalidCurrencyException("Unknown currency code: " + code);
    }


    public BigDecimal handleDealAmount(BigDecimal amount) {
        if(amount == null) {
            throw new InvalidCurrencyException("Invalid amount: " + amount);
        }
        return amount;
    }

}
