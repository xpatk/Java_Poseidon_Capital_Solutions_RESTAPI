package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Controller responsible for handling CurvePoint CRUD operations.
 */
@Controller
public class CurveController {

    private final CurvePointService curvePointService;


    /**
     * Constructor-based dependency injection of CurvePointService.
     *
     * @param curvePointService service handling business logic
     */
    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    /**
     * Displays the list of all CurvePoints.
     *
     * @param model Spring UI model
     * @return view name for curvePoint list page
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoints());
        return "curvePoint/list";
    }

    /**
     * Displays form to create a new CurvePoint.
     *
     * @param model Spring UI model
     * @return view name for add form
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
        model.addAttribute("curvePoint", new CurvePoint());
        return "curvePoint/add";
    }

    /**
     * Validates and saves a new CurvePoint.
     *
     * @param curvePoint object from form
     * @param result validation result
     * @param model Spring UI model
     * @return redirect to list if success, otherwise return form view
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.saveCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Displays update form for a specific CurvePoint.
     *
     * @param id CurvePoint identifier
     * @param model Spring UI model
     * @return view name for update form
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * Updates an existing CurvePoint.
     *
     * @param id CurvePoint identifier
     * @param curvePoint updated object
     * @param result validation result
     * @param model Spring UI model
     * @return redirect to list if success, otherwise return update form
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointService.saveCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Deletes a CurvePoint by id.
     *
     * @param id CurvePoint identifier
     * @return redirect to list view
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id) {
        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
