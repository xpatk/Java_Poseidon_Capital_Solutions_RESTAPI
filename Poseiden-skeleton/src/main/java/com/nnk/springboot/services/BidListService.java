package com.nnk.springboot.services;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for handling business logic related to BidList entity.
 */
@Service
public class BidListService {

    private final BidListRepository bidListRepository;


    /**
     * Constructor-based dependency injection of BidListRepository.
     *
     * @param bidListRepository repository for BidList persistence operations
     */
    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     * Retrieves all BidList entities from the database.
     *
     * @return list of BidList objects
     */
    public List<BidList> getAllBidLists() {
        return bidListRepository.findAll();
    }

    /**
     * Saves or updates a BidList entity.
     *
     * @param bidList entity to be saved
     * @return saved BidList entity
     */
    public BidList saveBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * Retrieves a BidList by its identifier.
     *
     * @param id BidList identifier
     * @return found BidList entity
     * @throws IllegalArgumentException if BidList is not found
     */
    public BidList findBidListById(Integer id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid id"));
    }

    /**
     * Deletes a BidList by its identifier.
     *
     * @param id BidList identifier
     */
    public void delete(Integer id) {
        bidListRepository.deleteById(id);
    }
}
