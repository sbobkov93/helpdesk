package com.example.helpdesk.controller;

import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.Client;
import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.entity.Status;
import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.service.ClientService;
import com.example.helpdesk.service.EmployeeService;
import com.example.helpdesk.service.StatusService;
import com.example.helpdesk.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private ClientService clientService;
    private EmployeeService employeeService;
    private StatusService statusService;
    private ModelMapper modelMapper;

    @Autowired
    public TicketController(TicketService ticketService, ClientService clientService, EmployeeService employeeService, StatusService statusService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.statusService = statusService;
        this.modelMapper = modelMapper;
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
    public String showFormForUpdate(@RequestParam("ticketId") int id, Model model){
        Optional<Ticket> ticket = ticketService.findById(id);
        if (ticket.isEmpty())
            return "redirect:/tickets";
        TicketDTO ticketDTO = modelMapper.map(ticket.get(), TicketDTO.class);
        model.addAttribute("ticket", ticketDTO);
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "ticket-form";
    }
//
//    @GetMapping("delete")
//    public String deleteClient(@RequestParam("employeeId") int id){
//        employeeService.delete(id);
//        return "redirect:/employees";
//    }

}
