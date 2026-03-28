package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controller responsible for handling RuleName CRUD operations.
 */
@Controller
public class RuleNameController {

    private final RuleNameService ruleNameService;

    /**
     * Constructor-based dependency injection of RuleNameService.
     *
     * @param ruleNameService service handling RuleName business logic
     */
    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    /**
     * Displays the list of all RuleName entities.
     *
     * @param model Spring UI model
     * @return view name for ruleName list page
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "ruleName/list";
    }

    /**
     * Displays form to create a new RuleName.
     *
     * @param model Spring UI model
     * @return view name for add form
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

    /**
     * Validates and saves a new RuleName.
     *
     * @param ruleName RuleName object from form
     * @param result validation result
     * @param model Spring UI model
     * @return redirect to list if success, otherwise return form view
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.saveRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Displays update form for a specific RuleName.
     *
     * @param id RuleName identifier
     * @param model Spring UI model
     * @return view name for update form
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findRuleNameById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * Updates an existing RuleName.
     *
     * @param id RuleName identifier
     * @param ruleName updated object
     * @param result validation result
     * @param model Spring UI model
     * @return redirect to list if success, otherwise return update form
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id,
                                 @Valid RuleName ruleName,
                                 BindingResult result,
                                 Model model) {

        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameService.saveRuleName(ruleName);

        return "redirect:/ruleName/list";
    }

    /**
     * Deletes a RuleName by its identifier.
     *
     * @param id RuleName identifier
     * @return redirect to list view
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {
        ruleNameService.delete(id);
        return "redirect:/ruleName/list";
    }
}