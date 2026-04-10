package com.nnk.springboot.services;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for handling business logic related to BidList entity.
 */
@Service
public class BidListService {

    private static final Logger logger = LoggerFactory.getLogger(BidListService.class);

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
        logger.info("Fetching all BidLists");
        return bidListRepository.findAll();
    }

    /**
     * Saves or updates a BidList entity.
     *
     * @param bidList entity to be saved
     * @return saved BidList entity
     */
    public BidList saveBidList(BidList bidList) {
        logger.info("Saving BidList: {}", bidList);
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
        logger.info("Fetching BidList with id: {}", id);
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid id"));
    }

    /**
     * Deletes a BidList by its identifier.
     *
     * @param id BidList identifier
     */
    public void delete(Integer id) {
        logger.warn("Deleting BidList with id: {}", id);
        bidListRepository.deleteById(id);
    }
}
