package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

/**
 * Service layer responsible for handling business logic related to CurvePoint entity.
 */
@Service
public class CurvePointService {

    private static final Logger logger = LoggerFactory.getLogger(CurvePointService.class);

    private final CurvePointRepository curvePointRepository;

    /**
     * Constructor-based dependency injection of CurvePointRepository.
     *
     * @param curvePointRepository repository for CurvePoint persistence operations
     */
    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     * Retrieves all CurvePoint entities from the database.
     *
     * @return list of CurvePoint objects
     */
    public List<CurvePoint> getAllCurvePoints() {
        logger.info("Fetching all CurvePoints");
        return curvePointRepository.findAll();
    }

    /**
     * Saves or updates a CurvePoint entity.
     *
     * @param curvePoint entity to be saved
     * @return saved CurvePoint entity
     */
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        logger.info("Saving CurvePoint: {}", curvePoint);
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Retrieves a CurvePoint by its identifier.
     *
     * @param id CurvePoint identifier
     * @return found CurvePoint entity
     * @throws IllegalArgumentException if CurvePoint is not found
     */
    public CurvePoint findCurvePointById(Integer id) {
        logger.info("Finding CurvePoint id={}", id);
        return curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint id."));
    }

    /**
     * Deletes a CurvePoint by its identifier.
     *
     * @param id CurvePoint identifier
     */
    public void delete(Integer id) {
        logger.warn("Deleting CurvePoint id={}", id);
        curvePointRepository.deleteById(id);
    }
}
