package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller responsible for handling authentication-related pages
 * such as login and access denied (error) views.
 */
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Displays the login page.
     *
     * @return ModelAndView pointing to login template
     */
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    /**
     * Displays a secured page with a list of users.
     * Accessible only to authorized users.
     *
     * @return ModelAndView containing list of users
     */
    @GetMapping("/secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     * Displays access denied page when user tries to access
     * a restricted resource.
     *
     * @return ModelAndView pointing to 403 error page
     */
    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
