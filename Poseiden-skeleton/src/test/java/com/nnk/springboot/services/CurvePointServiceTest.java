package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
 * Unit tests for {@link CurvePointService}.
 */
@ExtendWith(MockitoExtension.class)
class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    @Test
    void shouldReturnAllCurvePoints() {
        List<CurvePoint> list = List.of(new CurvePoint(), new CurvePoint());

        when(curvePointRepository.findAll()).thenReturn(list);

        List<CurvePoint> result = curvePointService.getAllCurvePoints();

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldSaveCurvePoint() {
        CurvePoint cp = new CurvePoint(1, 10d, 20d);

        when(curvePointRepository.save(cp)).thenReturn(cp);

        CurvePoint result = curvePointService.saveCurvePoint(cp);

        verify(curvePointRepository).save(cp);
        assertThat(result).isEqualTo(cp);
    }

    @Test
    void shouldFindCurvePointById() {
        CurvePoint cp = new CurvePoint(1, 10d, 20d);

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(cp));

        CurvePoint result = curvePointService.findCurvePointById(1);

        assertThat(result).isEqualTo(cp);
    }

    @Test
    void shouldThrowWhenCurvePointNotFound() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> curvePointService.findCurvePointById(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldDeleteCurvePoint() {
        curvePointService.delete(1);

        verify(curvePointRepository).deleteById(1);
    }
}