package com.example.helpdesk.controller;

import com.example.helpdesk.service.DtoUtils;
import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.*;
import com.example.helpdesk.service.*;
import com.example.helpdesk.validation.TicketUpdateValidatorService;
import com.example.helpdesk.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final StatusService statusService;
    private final DtoUtils dtoUtils;
    private final TicketUpdateValidatorService ticketUpdateValidator;

    @Autowired
    public TicketController(TicketService ticketService, ClientService clientService,
                            EmployeeService employeeService, StatusService statusService, DtoUtils dtoUtils, TicketUpdateValidatorService ticketUpdateValidator) {
        this.ticketService = ticketService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.statusService = statusService;
        this.dtoUtils = dtoUtils;
        this.ticketUpdateValidator = ticketUpdateValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    //FIXME hibernate generate multiple queries when list is not empty. Can't figure out why
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("tickets", ticketService.findAll());
        return "tickets";
    }

    @GetMapping("create")
    public String showFormForCreate(Model model){
        model.addAttribute("ticket", new TicketDTO());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "ticket-form";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute("ticket") TicketDTO ticketDTO,
                              BindingResult bindingResult,
                              Model model,
                              Authentication authentication){
        if (bindingResult.hasErrors()) {
            model.addAttribute("clients", clientService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            return "ticket-form";
        }
        if (ticketDTO.getId() != null)
            return update(ticketDTO, bindingResult, authentication);
        String creatorUserName = authentication.getName();
        Employee creator = employeeService.findByAuthenticationDataUserName(creatorUserName);
        Ticket ticket = dtoUtils.getTicket(ticketDTO, creator);
        ticketService.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("update")
    public String showFormForUpdate(@RequestParam("ticketId") int id, Model model, Authentication authentication){
        Optional<Ticket> optionalTicket = ticketService.findByIdWithNotes(id);
        if (optionalTicket.isEmpty())
            return "redirect:/tickets";
        Ticket ticket = optionalTicket.get();
        TicketDTO ticketDTO = dtoUtils.getTicketDTO(authentication, ticket);
        model.addAttribute("ticket", ticketDTO);
        model.addAttribute("notes", ticket.getNotes());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "ticket-form";
    }

    public String update(@Valid TicketDTO ticketDTO,
                         BindingResult bindingResult,
                         Authentication authentication){
        ValidationResult result = ticketUpdateValidator.validate(ticketDTO, authentication);
        if (!result.isValid()){
            bindingResult.addError(new ObjectError("customValidationError", result.getErrorMessage()));
            return "invalid";
        }
        Ticket ticket = dtoUtils.getTicket(ticketDTO);
        ticketService.save(ticket);
        return "redirect:/tickets";
    }

}
