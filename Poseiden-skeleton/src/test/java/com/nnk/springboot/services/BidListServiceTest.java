package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link BidListService} using mocked repository.
 */
@ExtendWith(MockitoExtension.class)
class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    /**
     * Should return all BidLists.
     */
    @Test
    void shouldReturnAllBidLists() {

        List<BidList> bids = List.of(new BidList(), new BidList());

        when(bidListRepository.findAll()).thenReturn(bids);

        List<BidList> result = bidListService.getAllBidLists();

        assertThat(result).hasSize(2);
    }

    /**
     * Should save a BidList.
     */
    @Test
    void shouldSaveBidList() {

        BidList bid = new BidList("Account", "Type", 10d);

        when(bidListRepository.save(bid)).thenReturn(bid);

        BidList result = bidListService.saveBidList(bid);

        verify(bidListRepository).save(bid);
        assertThat(result).isEqualTo(bid);
    }

    /**
     * Should return BidList by id when it exists.
     */
    @Test
    void shouldFindBidListById() {

        BidList bid = new BidList("Account", "Type", 10d);

        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));

        BidList result = bidListService.findBidListById(1);

        assertThat(result).isEqualTo(bid);
    }

    /**
     * Should throw exception when BidList is not found.
     */
    @Test
    void shouldThrowWhenBidListNotFound() {

        when(bidListRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bidListService.findBidListById(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid bid id");
    }

    /**
     * Should delete BidList by id.
     */
    @Test
    void shouldDeleteBidList() {

        bidListService.delete(1);

        verify(bidListRepository).deleteById(1);
    }
}