package me.ersystem.controller;

import me.ersystem.dto.EmployeeDto;
import me.ersystem.dto.IncidentDto;
import me.ersystem.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MotYim mohamed.motyim@gmail.com
 * @since 18-Mar-2019
 */
@Controller
public class IncidentController {

    @Autowired
    IncidentService service;

    @GetMapping("incident")
    public String viewAllIncident(Model model, HttpSession session){
        EmployeeDto loginUser =(EmployeeDto) session.getAttribute("loginUser");
        String status = loginUser.getRole().equals("admin")? "open":"new";
        List<IncidentDto> allIncident = service.getAllIncidentByStatus(status);
        model.addAttribute("incidents",allIncident);
        return "incident";
    }

    @GetMapping("incident/{id}")
    public String viewIncident(@PathVariable("id") int id, Model model){
        IncidentDto incidentDto = service.getIncidentById(id);
        model.addAttribute("incident",incidentDto);
        return "viewincident";
    }

    @GetMapping("incident/{id}/delete")
    public String deleteIncident(@PathVariable("id") int id){
        service.changeStatus(id,"rejected");
        return "redirect:/incident";
    }

    @GetMapping("incident/{id}/open")
    public String openIncident(@PathVariable("id") int id){
        service.changeStatus(id,"open");
        return "redirect:/incident";
    }

    @GetMapping("incident/{id}/approve")
    public String approveIncident(@PathVariable("id") int id){
        service.changeStatus(id,"approved");
        return "redirect:/incident";
    }
}
