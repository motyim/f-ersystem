package me.ersystem.controller;

import me.ersystem.dto.EmployeeDto;
import me.ersystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 *
 * @since 16-Feb-19
 */
@Controller
public class AccountController {

    @Autowired
    EmployeeService service;

    @GetMapping("/account")
    public String displayAccount(Model model, HttpSession session) {
        EmployeeDto loginUser =(EmployeeDto) session.getAttribute("loginUser");
        EmployeeDto dto = service.findAccount(loginUser.getId());
        model.addAttribute("user", dto);
        return "account";
    }

    @PostMapping("/account")
    public String editAccount(@ModelAttribute EmployeeDto dto,Model model) {
        service.updateEmployee(dto);
        model.addAttribute("user", dto);
        return "account";
    }


}
