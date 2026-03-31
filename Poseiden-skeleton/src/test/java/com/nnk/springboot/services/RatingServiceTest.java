package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
 * Unit tests for {@link RatingService}.
 */
@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @Test
    void shouldReturnAllRatings() {
        List<Rating> list = List.of(new Rating(), new Rating());

        when(ratingRepository.findAll()).thenReturn(list);

        List<Rating> result = ratingService.getAllRatings();

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldSaveRating() {
        Rating rating = new Rating("A", "B", "C", 1);

        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.saveRating(rating);

        verify(ratingRepository).save(rating);
        assertThat(result).isEqualTo(rating);
    }

    @Test
    void shouldFindRatingById() {
        Rating rating = new Rating("A", "B", "C", 1);

        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Rating result = ratingService.findRatingById(1);

        assertThat(result).isEqualTo(rating);
    }

    @Test
    void shouldThrowWhenRatingNotFound() {
        when(ratingRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ratingService.findRatingById(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldDeleteRating() {
        ratingService.delete(1);

        verify(ratingRepository).deleteById(1);
    }
}