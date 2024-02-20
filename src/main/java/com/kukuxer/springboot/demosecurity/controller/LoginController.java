package com.kukuxer.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController    {
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return "plain-login";
    }
//    acces denied page
    @GetMapping("/acces-denied")
    public String showAccesdeniedPage(){
        return "acces-denied";
    }
}
