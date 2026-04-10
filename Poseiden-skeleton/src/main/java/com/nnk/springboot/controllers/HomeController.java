package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible for handling home page navigation.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Displays the public home page.
     *
     * @param model Spring UI model
     * @return home view
     */
    @RequestMapping("/")
    public String home(Model model) {
        logger.info("User accessed home page");
        return "home";
    }

    /**
     * Redirects admin users to the main application page.
     *
     * @param model Spring UI model
     * @return redirect to BidList page
     */
    @RequestMapping("/admin/home")
    public String adminHome(Model model) {
        logger.info("Admin redirected to BidList");
        return "redirect:/bidList/list";
    }
}