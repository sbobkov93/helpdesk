package com.example.helpdesk.controller;

import com.example.helpdesk.dto.NoteDTO;
import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.Note;
import com.example.helpdesk.service.EmployeeService;
import com.example.helpdesk.service.NoteService;
import com.example.helpdesk.service.StatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;
    private EmployeeService employeeService;
    private StatusService statusService;
    private ModelMapper modelMapper;

    @Autowired
    public NoteController(NoteService noteService, EmployeeService employeeService, StatusService statusService, ModelMapper modelMapper) {
        this.noteService = noteService;
        this.employeeService = employeeService;
        this.statusService = statusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("noteId") int id, HttpServletRequest request, Model model){
        Optional<Note> note = noteService.findById(id);
        if (note.isEmpty())
            return "redirect:" + request.getHeader("Referer");
        NoteDTO noteDTO = modelMapper.map(note.get(), NoteDTO.class);
        model.addAttribute("note", noteDTO);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "note-form";
    }

    @GetMapping("create")
    public String showFormForCreate(Model model, TicketDTO ticketDTO){
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTicket(ticketDTO.getId());
        model.addAttribute("note", noteDTO);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "note-form";
    }

}
