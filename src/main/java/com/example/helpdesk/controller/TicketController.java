package com.example.helpdesk.controller;

import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.service.ClientService;
import com.example.helpdesk.service.EmployeeService;
import com.example.helpdesk.service.StatusService;
import com.example.helpdesk.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private ClientService clientService;
    private EmployeeService employeeService;
    private StatusService statusService;

    @Autowired
    public TicketController(TicketService ticketService, ClientService clientService, EmployeeService employeeService, StatusService statusService) {
        this.ticketService = ticketService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.statusService = statusService;
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("tickets", ticketService.findAll());
        return "tickets";
    }

    @GetMapping("create")
    public String showForm(Model model){
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "ticket-form";
    }

//    @PostMapping("form")
//    public String processForm(Employee employee){
//        employeeService.save(employee);
//        return "redirect:/employees";
//    }
//
//    @GetMapping("update")
//    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){
//        model.addAttribute("employee", employeeService.getById(id));
//        return "employee-form";
//    }
//
//    @GetMapping("delete")
//    public String deleteClient(@RequestParam("employeeId") int id){
//        employeeService.delete(id);
//        return "redirect:/employees";
//    }

}
