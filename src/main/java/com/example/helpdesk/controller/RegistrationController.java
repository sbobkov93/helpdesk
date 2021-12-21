package com.example.helpdesk.controller;

import com.example.helpdesk.dto.UserRegistrationDTO;
import com.example.helpdesk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private RoleService roleService;


    @Autowired
    public RegistrationController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String showForm(Model model){
        model.addAttribute( "user", new UserRegistrationDTO());
        model.addAttribute("roles", roleService.findAll());
        return "registration";
    }

    @PostMapping
    public String processForm(@ModelAttribute @Valid UserRegistrationDTO user,
                              BindingResult bindingResult,
                              Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "registration";
        }

        return "redirect:/employees";
    }

}
