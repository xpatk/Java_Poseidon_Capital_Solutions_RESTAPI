package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Controller responsible for handling BidList CRUD operations.
 */
@Controller
public class BidListController {

    private final BidListService bidListService;


    /**
     * Constructor-based dependency injection of BidListService.
     *
     * @param bidListService service handling business logic
     */
    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    /**
     * Displays the list of all BidLists.
     *
     * @param model Spring UI model
     * @return view name for bid list page
     */
    @RequestMapping("/bidList/list")
    public String list(Model model)
    {
        model.addAttribute("bidLists", bidListService.getAllBidLists());
        return "bidList/list";
    }

    /**
     * Displays form to create a new BidList.
     *
     * @param model Spring UI model
     * @return view name for add form
     */
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    /**
     * Validates and saves a new BidList.
     *
     * @param bid BidList object from form
     * @param result validation result
     * @param model Spring UI model
     * @return redirect to list if success, otherwise return form view
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.saveBidList(bid);
        return "redirect:/bidList/list";
    }

    /**
     * Displays update form for a specific BidList.
     *
     * @param id BidList identifier
     * @param model Spring UI model
     * @return view name for update form
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findBidListById(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Updates an existing BidList.
     *
     * @param id BidList identifier
     * @param bidList updated object
     * @param result validation result
     * @param model Spring UI model
     * @return redirect to list if success, otherwise return update form
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid BidList bidList,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setId(id);
        bidListService.saveBidList(bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Deletes a BidList by id.
     *
     * @param id BidList identifier
     * @return redirect to list view
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        bidListService.delete(id);
        return "redirect:/bidList/list";
    }
}
