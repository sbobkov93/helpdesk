package com.example.helpdesk.controller;

import com.example.helpdesk.entity.Client;
import com.example.helpdesk.service.ClientService;
import com.example.helpdesk.validation.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientValidator clientValidator;

    @Autowired
    public ClientController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("clients", clientService.findAll());
        return "clients";
    }

    @GetMapping("form")
    public String showForm(Model model){
        model.addAttribute("client", new Client());
        return "client-form";
    }

    @PostMapping("form")
    public String processForm(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "client-form";
        }
        List<ObjectError> validationResult = clientValidator.validate(client);
        if (!validationResult.isEmpty()){
            validationResult.forEach(bindingResult::addError);
            return "client-form";
        }
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("update")
    public String showFormForUpdate(@RequestParam("clientId") int id, Model model){
        Optional<Client> optionalClient = clientService.findById(id);
        if (optionalClient.isEmpty())
            return "redirect:/clients";
        model.addAttribute("client", optionalClient.get());
        return "client-form";
    }

}
