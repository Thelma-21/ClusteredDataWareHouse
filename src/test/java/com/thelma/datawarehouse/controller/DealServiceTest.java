package com.thelma.datawarehouse.controller;

import com.thelma.datawarehouse.dto.DealRequestDto;
import com.thelma.datawarehouse.exception.CustomAlreadyExistException;
import com.thelma.datawarehouse.exception.CustomNotFoundException;
import com.thelma.datawarehouse.entity.Deal;
import com.thelma.datawarehouse.repository.DealRepo;
import com.thelma.datawarehouse.service.impl.DealServiceImpl;
import com.thelma.datawarehouse.utils.ValidateUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
public class DealServiceTest {
       @InjectMocks
    private DealServiceImpl dealService;
    @Mock
    private DealRepo dealRepo;
    @Mock
    private Deal deals;
    @Mock
    private ValidateUtil util;

    @BeforeEach
void init(){
        LocalDateTime localDateTime = LocalDateTime.of(2022, 12, 2, 1, 34, 19);
     deals = new Deal(1L,"234","USD","NGN", BigDecimal.valueOf(340), localDateTime);
    when(dealRepo.save(deals)).thenReturn(deals);
}

    @Test
    @DisplayName("should save the deal details")
    void saveDetails()  {

        var dto = new DealRequestDto(util.validateCurrency("USD"), util.validateCurrency("NGN"), util.handleDealAmount(BigDecimal.valueOf(340)));
        when(dealRepo.findByDealId(deals.getDealId())).thenReturn(Optional.empty());
        var actual = dealService.saveDealDetails(dto);
        assertEquals("success", actual.getMessage());

    }

   @Test
   void testToFindDealDetailsById(){
       when(dealRepo.findByDealId(deals.getDealId())).thenReturn(Optional.of(deals));
       var actual = dealService.getDealById("234");
       assertEquals("success", actual.getMessage());
   }


    @Test
    void shouldThrowDealAlreadyExistException() {
        DealRequestDto dto = new DealRequestDto("USD", "NGN", BigDecimal.valueOf(340));
        when(dealRepo.findByDealId(deals.getDealId())).thenReturn(Optional.of(deals));
        assertThrows(CustomAlreadyExistException.class, () -> dealService.saveDealDetails(dto));

    }

    @Test
    void shouldThrowDealNotFoundException(){
        when(dealRepo.findByDealId(deals.getDealId())).thenReturn(Optional.of(deals));
        assertThrows(CustomNotFoundException.class, () -> dealService.getDealById("230"));
    }
}
