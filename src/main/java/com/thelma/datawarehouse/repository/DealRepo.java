package com.thelma.datawarehouse.repository;

import com.thelma.datawarehouse.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealRepo extends JpaRepository<Deal, Long> {
    Optional<Deal> findByDealId(String dealId);
}
