package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Controller responsible for handling Rating CRUD operations.
 */
@Controller
public class RatingController {

    private final RatingService ratingService;

    /**
     * Constructor-based dependency injection of RatingService.
     *
     * @param ratingService service handling Rating business logic
     */
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    /**
     * Displays the list of all Rating entities.
     *
     * @param model Spring UI model
     * @return view name for rating list page
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingService.getAllRatings());
        return "rating/list";
    }

    /**
     * Displays form to create a new Rating.
     *
     * @param model Spring UI model
     * @return view name for add form
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    /**
     * Validates and saves a new Rating.
     *
     * @param rating Rating object from form
     * @param result validation result
     * @param model Spring UI model
     * @return redirect to list if success, otherwise return form view
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/add";
        }
        ratingService.saveRating(rating);
        return "redirect:/rating/list";
    }

    /**
     * Displays update form for a specific Rating.
     *
     * @param id Rating identifier
     * @param model Spring UI model
     * @return view name for update form
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findRatingById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Updates an existing Rating.
     *
     * @param id Rating identifier
     * @param rating updated object
     * @param result validation result
     * @param model Spring UI model
     * @return redirect to list if success, otherwise return update form
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }
        rating.setId(id);
        ratingService.saveRating(rating);

        return "redirect:/rating/list";
    }

    /**
     * Deletes a Rating by its identifier.
     *
     * @param id Rating identifier
     * @return redirect to list view
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {
        ratingService.delete(id);
        return "redirect:/rating/list";
    }
}
