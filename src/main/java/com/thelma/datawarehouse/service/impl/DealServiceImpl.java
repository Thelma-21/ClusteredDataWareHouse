package com.thelma.datawarehouse.service.impl;

import com.thelma.datawarehouse.dto.DealDetailsDto;
import com.thelma.datawarehouse.dto.DealRequestDto;
import com.thelma.datawarehouse.exception.CustomAlreadyExistException;
import com.thelma.datawarehouse.exception.CustomNotFoundException;
import com.thelma.datawarehouse.entity.Deal;
import com.thelma.datawarehouse.repository.DealRepo;
import com.thelma.datawarehouse.response.ApiResponse;
import com.thelma.datawarehouse.service.DealService;
import com.thelma.datawarehouse.utils.ValidateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.springframework.http.HttpStatus.CREATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {
private final DealRepo dealRepo;

private final ValidateUtil validateUtil;


    public ApiResponse<?> saveDealDetails(DealRequestDto dto)  {
        String id = UUID.randomUUID().toString();
        Optional<Deal> existingDealDetails = dealRepo.findByDealId(id);
        if(existingDealDetails.isPresent()) {
            log.error("Deal with Id number: " + id + " already exist");
            throw new CustomAlreadyExistException("Deal with Id number: " + id + " already exist");
        }
        else {
            Deal deal = Deal.builder()
                    .dealId(id)
                    .orderingCurrency(validateUtil.validateCurrency(dto.getOrderingCurrency()))
                    .convertedCurrency(validateUtil.validateCurrency(dto.getConvertedCurrency()))
                    .amount(validateUtil.handleDealAmount(dto.getAmount()))
                    .build();
            dealRepo.save(deal);
            log.info("deal details saved successfully");

            return new ApiResponse<>("success" , LocalDateTime.now(), CREATED);
        }

    }


    public ApiResponse<List<DealDetailsDto>> getAllDeals(){
        List<Deal> allDeals = dealRepo.findAll();
        log.info("All deals retrieved successfully");
        return new ApiResponse<>("success",LocalDateTime.now(),allDeals.stream().map(this::mapToDealDetailsDto).toList());
    }

    private DealDetailsDto mapToDealDetailsDto(Deal deal){
        return DealDetailsDto.builder()
                .dealId(deal.getDealId())
                .orderingCurrency(deal.getOrderingCurrency())
                .convertedCurrency(deal.getConvertedCurrency())
                .amount(deal.getAmount())
                .dealTimeStamp(deal.getDealTimeStamp())
                .build();
    }

    @Override
    public ApiResponse<DealDetailsDto> getDealById(String id){
        Deal deal = dealRepo.findByDealId(id).orElseThrow(() -> new CustomNotFoundException("Deal with Id number: " + id + " does not exist"));
        log.info("Deal with Id number: " + id + " retrieved successfully");
        return new ApiResponse<>("success",LocalDateTime.now(),mapToDealDetailsDto(deal));
    }


}
