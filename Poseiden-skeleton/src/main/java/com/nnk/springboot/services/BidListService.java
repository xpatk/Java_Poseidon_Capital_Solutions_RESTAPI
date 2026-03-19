package com.nnk.springboot.services;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import java.util.List;

public class BidListService {

    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public BidList findById(Integer id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid id"));
    }

    public void delete(Integer id) {
        bidListRepository.deleteById(id);
    }
}
