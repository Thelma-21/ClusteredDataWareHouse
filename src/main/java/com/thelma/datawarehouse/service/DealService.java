package com.thelma.datawarehouse.service;

import com.thelma.datawarehouse.dto.DealDetailsDto;
import com.thelma.datawarehouse.dto.DealRequestDto;
import com.thelma.datawarehouse.response.ApiResponse;

import java.util.List;

public interface DealService {
    ApiResponse<?> saveDealDetails(DealRequestDto dto) throws Exception;

    ApiResponse<List<DealDetailsDto>> getAllDeals();

    ApiResponse<DealDetailsDto> getDealById(String id);

}
