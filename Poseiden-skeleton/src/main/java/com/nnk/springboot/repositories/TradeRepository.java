package com.nnk.springboot.repositories;

import java.com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
