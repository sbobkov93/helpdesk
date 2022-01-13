package com.example.helpdesk.controller;

import com.example.helpdesk.dto.EmployeeDTO;
import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.service.DtoUtils;
import com.example.helpdesk.service.EmployeeService;
import com.example.helpdesk.service.RoleService;
import com.example.helpdesk.validation.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private RoleService roleService;
    private DtoUtils dtoUtils;
    private EmployeeValidator employeeValidator;

    @Autowired
    public EmployeeController(EmployeeService clientService, RoleService roleService, DtoUtils dtoUtils, EmployeeValidator employeeValidator) {
        this.employeeService = clientService;
        this.roleService = roleService;
        this.dtoUtils = dtoUtils;
        this.employeeValidator = employeeValidator;
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

    @GetMapping("create")
    public String showForm(Model model){
        model.addAttribute( "employee", new EmployeeDTO());
        model.addAttribute("roles", roleService.findAll());
        return "employee-form";
    }

    @PostMapping("create")
    public String processForm(@Valid @ModelAttribute("employee") EmployeeDTO employee,
                              BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "employee-form";
        }
        List<ObjectError> validationResult = employeeValidator.validate(employee);
        if (!validationResult.isEmpty()){
            validationResult.forEach(bindingResult::addError);
            model.addAttribute("roles", roleService.findAll());
            return "employee-form";
        }
        Employee newUser = dtoUtils.getEmployee(employee);
        employeeService.save(newUser);
        return "redirect:/employees";
    }

    @GetMapping("update")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model, Authentication authentication){
        Employee employee = employeeService.findByIdEager(id);
        EmployeeDTO employeeDto = dtoUtils.getEmployeeDto(employee, authentication);
        model.addAttribute("employee", employeeDto);
        model.addAttribute("roles", roleService.findAll());
        return "employee-form";
    }

    @GetMapping("delete")
    public String deleteClient(@RequestParam("employeeId") int id){
        employeeService.delete(id);
        return "redirect:/employees";
    }

}
