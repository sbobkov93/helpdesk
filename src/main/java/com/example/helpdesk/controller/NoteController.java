package com.example.helpdesk.controller;

import com.example.helpdesk.dto.NoteDTO;
import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.entity.Note;
import com.example.helpdesk.service.DtoUtils;
import com.example.helpdesk.service.EmployeeService;
import com.example.helpdesk.service.NoteService;
import com.example.helpdesk.service.StatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;
    private EmployeeService employeeService;
    private StatusService statusService;
    private ModelMapper modelMapper;
    private DtoUtils dtoUtils;

    @Autowired
    public NoteController(NoteService noteService, EmployeeService employeeService, StatusService statusService, ModelMapper modelMapper, DtoUtils dtoUtils) {
        this.noteService = noteService;
        this.employeeService = employeeService;
        this.statusService = statusService;
        this.modelMapper = modelMapper;
        this.dtoUtils = dtoUtils;
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("noteId") int id,
                                    HttpServletRequest request,
                                    Model model,
                                    Authentication authentication){
        Optional<Note> optionalNote = noteService.findById(id);
        if (optionalNote.isEmpty())
            return "redirect:" + request.getHeader("Referer");
        Note note = optionalNote.get();
        NoteDTO noteDTO = dtoUtils.getNoteDTO(authentication, note);
        model.addAttribute("note", noteDTO);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "note-form";
    }

    @GetMapping("create")
    public String showFormForCreate(@RequestParam("ticketId") int id, Model model, Authentication authentication){
        NoteDTO noteDTO = new NoteDTO();
        Employee employee = employeeService.findByAuthenticationDataUserName(authentication.getName());
        noteDTO.setTicket(id);
        noteDTO.setCreator(employee.getId());
        model.addAttribute("note", noteDTO);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "note-form";
    }

    @PostMapping("create")
    public String create (@Valid @ModelAttribute("note") NoteDTO noteDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("employees", employeeService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            return "note-form";
        }
        Note note = modelMapper.map(noteDTO, Note.class);
        noteService.save(note);
        return "redirect:/tickets/update?ticketId=" + noteDTO.getTicket();
    }

}
