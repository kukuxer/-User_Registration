package com.kukuxer.springboot.demosecurity.controller;

import com.kukuxer.springboot.demosecurity.entity.User;
import com.kukuxer.springboot.demosecurity.service.UserService;
import com.kukuxer.springboot.demosecurity.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(getClass().getName());

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("webUser", new WebUser());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") WebUser theWebUser,
            BindingResult theBindingResult,
            HttpSession session, Model theModel) {

        String userName = theWebUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()){
            return "registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
            theModel.addAttribute("webUser", new WebUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "registration-form";
        }

        // create user account and store in the databse
        userService.save(theWebUser);

        logger.info("Successfully created user: " + userName);

        // place user in the web http session for later use
        session.setAttribute("user", theWebUser);

        return "registration-confirmation";
    }
}
