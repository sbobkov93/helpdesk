package com.example.helpdesk.controller;

import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.*;
import com.example.helpdesk.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private ClientService clientService;
    private EmployeeService employeeService;
    private StatusService statusService;
    private ModelMapper modelMapper;
    private NoteService noteService;

    @Autowired
    public TicketController(TicketService ticketService, ClientService clientService, EmployeeService employeeService, StatusService statusService, ModelMapper modelMapper, NoteService noteService) {
        this.ticketService = ticketService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.statusService = statusService;
        this.modelMapper = modelMapper;
        this.noteService = noteService;
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
    public String showForm(Model model){
        model.addAttribute("ticket", new TicketDTO());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "ticket-form";
    }

    @PostMapping("create")
    public String processForm(@Valid @ModelAttribute("ticket") TicketDTO ticketDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("clients", clientService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            return "ticket-form";
        }
        ticketDTO.setCreator(1); //TODO
        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        ticketService.save(ticket);
        return "redirect:/tickets";
    }


    @GetMapping("update")
    public String showFormForUpdate(@RequestParam("ticketId") int id, Model model, Authentication authentication){
        Optional<Ticket> ticket = ticketService.findById(id);
        if (ticket.isEmpty())
            return "redirect:/tickets";
        boolean readOnly = isReadOnly(authentication, ticket.get());
        TicketDTO ticketDTO = modelMapper.map(ticket.get(), TicketDTO.class);
        ticketDTO.setReadOnly(readOnly);
        model.addAttribute("ticket", ticketDTO);
        model.addAttribute("notes", noteService.getByTicketId(id));
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "ticket-form";
    }

    private boolean isReadOnly(Authentication authentication, Ticket ticket) {
        String ownerUserName = ticket.getOwner().getAuthenticationData().getUserName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(UserRole.ROLE_ADMIN.name()));
        return !authentication.getName().equals(ownerUserName) && !isAdmin;
    }

}
