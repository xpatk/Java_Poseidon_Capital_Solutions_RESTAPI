package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link TradeService}.
 */
@ExtendWith(MockitoExtension.class)
class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @Test
    void shouldReturnAllTrades() {
        List<Trade> list = List.of(new Trade(), new Trade());

        when(tradeRepository.findAll()).thenReturn(list);

        List<Trade> result = tradeService.getAllTrades();

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldSaveTrade() {
        Trade trade = new Trade("acc", "type");

        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade result = tradeService.saveTrade(trade);

        verify(tradeRepository).save(trade);
        assertThat(result).isEqualTo(trade);
    }

    @Test
    void shouldFindTradeById() {
        Trade trade = new Trade("acc", "type");

        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Trade result = tradeService.findTradeById(1);

        assertThat(result).isEqualTo(trade);
    }

    @Test
    void shouldThrowWhenTradeNotFound() {
        when(tradeRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tradeService.findTradeById(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldDeleteTrade() {
        tradeService.delete(1);

        verify(tradeRepository).deleteById(1);
    }
}