package com.nnk.springboot.services;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> getAllBidLists() {
        return bidListRepository.findAll();
    }

    public BidList saveBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public BidList findBidListById(Integer id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid id"));
    }

    public void delete(Integer id) {
        bidListRepository.deleteById(id);
    }
}
