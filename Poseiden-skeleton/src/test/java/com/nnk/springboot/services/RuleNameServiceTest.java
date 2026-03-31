package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
 * Unit tests for {@link RuleNameService}.
 */
@ExtendWith(MockitoExtension.class)
class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameService ruleNameService;

    @Test
    void shouldReturnAllRuleNames() {
        List<RuleName> list = List.of(new RuleName(), new RuleName());

        when(ruleNameRepository.findAll()).thenReturn(list);

        List<RuleName> result = ruleNameService.getAllRuleNames();

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldSaveRuleName() {
        RuleName rule = new RuleName("n", "d", "j", "t", "s", "sp");

        when(ruleNameRepository.save(rule)).thenReturn(rule);

        RuleName result = ruleNameService.saveRuleName(rule);

        verify(ruleNameRepository).save(rule);
        assertThat(result).isEqualTo(rule);
    }

    @Test
    void shouldFindRuleNameById() {
        RuleName rule = new RuleName("n", "d", "j", "t", "s", "sp");

        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));

        RuleName result = ruleNameService.findRuleNameById(1);

        assertThat(result).isEqualTo(rule);
    }

    @Test
    void shouldThrowWhenRuleNameNotFound() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ruleNameService.findRuleNameById(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldDeleteRuleName() {
        ruleNameService.delete(1);

        verify(ruleNameRepository).deleteById(1);
    }
}