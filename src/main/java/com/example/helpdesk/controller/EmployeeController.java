package com.example.helpdesk.controller;

import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService clientService) {
        this.employeeService = clientService;
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

    @GetMapping("create")
    public String showForm(Model model){
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("create")
    public String processForm(Employee employee){
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("update")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isEmpty())
            return "redirect:/employees";
        model.addAttribute("employee", employee.get());
        return "employee-form";
    }

    @GetMapping("delete")
    public String deleteClient(@RequestParam("employeeId") int id){
        employeeService.delete(id);
        return "redirect:/employees";
    }

}
