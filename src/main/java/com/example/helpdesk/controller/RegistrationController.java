package com.example.helpdesk.controller;

import com.example.helpdesk.dto.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @GetMapping
    public String showForm(Model model){
        model.addAttribute( "user", new UserRegistrationDTO());
        return "registration";
    }

    @PostMapping
    public String processForm(@ModelAttribute @Valid UserRegistrationDTO user){
        return null;
    }

}
