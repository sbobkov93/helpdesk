package com.example.helpdesk.controller;

import com.example.helpdesk.entity.Client;
import com.example.helpdesk.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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
    public String processForm(Client client){
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("update")
    public String showFormForUpdate(@RequestParam("clientId") int id, Model model){
        model.addAttribute("client", clientService.getById(id));
        return "client-form";
    }

}
