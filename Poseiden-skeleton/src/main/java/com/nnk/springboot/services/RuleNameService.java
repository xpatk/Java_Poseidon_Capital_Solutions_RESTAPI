package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for handling business logic related to RuleName entity.
 */
@Service
public class RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    /**
     * Constructor-based dependency injection of RuleNameRepository.
     *
     * @param ruleNameRepository repository for RuleName persistence operations
     */
    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    /**
     * Retrieves all RuleName entities from the database.
     *
     * @return list of RuleName objects
     */
    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * Saves or updates a RuleName entity.
     *
     * @param ruleName entity to be saved
     * @return saved RuleName entity
     */
    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Retrieves a RuleName by its identifier.
     *
     * @param id RuleName identifier
     * @return found RuleName entity
     * @throws IllegalArgumentException if RuleName is not found
     */
    public RuleName findRuleNameById(Integer id) {
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rule name id"));
    }

    /**
     * Deletes a RuleName by its identifier.
     *
     * @param id RuleName identifier
     */
    public void delete(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}