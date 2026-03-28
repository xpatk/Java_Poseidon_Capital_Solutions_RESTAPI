package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for handling business logic related to Trade entity.
 */
@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    /**
     * Constructor-based dependency injection of TradeRepository.
     *
     * @param tradeRepository repository for Trade persistence operations
     */
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Retrieves all Trade entities.
     *
     * @return list of Trade objects
     */
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    /**
     * Saves or updates a Trade entity.
     *
     * @param trade entity to save
     * @return saved Trade
     */
    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Finds a Trade by id.
     *
     * @param id Trade identifier
     * @return Trade entity
     * @throws IllegalArgumentException if not found
     */
    public Trade findTradeById(Integer id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trade id"));
    }

    /**
     * Deletes a Trade by id.
     *
     * @param id Trade identifier
     */
    public void delete(Integer id) {
        tradeRepository.deleteById(id);
    }
}