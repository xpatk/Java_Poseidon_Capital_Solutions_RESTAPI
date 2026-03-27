package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for handling business logic related to Rating entity.
 */
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    /**
     * Constructor-based dependency injection of RatingRepository.
     *
     * @param ratingRepository repository for Rating persistence operations
     */
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Retrieves all Rating entities from the database.
     *
     * @return list of Rating objects
     */
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Saves or updates a Rating entity.
     *
     * @param rating entity to be saved
     * @return saved Rating entity
     */
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Retrieves a Rating by its identifier.
     *
     * @param id Rating identifier
     * @return found Rating entity
     * @throws IllegalArgumentException if Rating is not found
     */
    public Rating findRatingById(Integer id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating id"));
    }

    /**
     * Deletes a Rating by its identifier.
     *
     * @param id Rating identifier
     */
    public void delete(Integer id) {
        ratingRepository.deleteById(id);
    }
}