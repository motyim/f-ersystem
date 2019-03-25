package me.ersystem.controller;

import me.ersystem.dto.EmployeeDto;
import me.ersystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * @since 14-Mar-2019
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService service ;


    @GetMapping("/employee")
    public String displayAccount(Model model) {
        List<EmployeeDto> accounts = service.getAllAccounts();
        model.addAttribute("users",accounts);
        return "employee";
    }

    @PostMapping("/employee")
    public String editAccount(@RequestParam int id, Model model) {
        EmployeeDto account = service.findAccount(id);
        model.addAttribute("users", account);
        return "employee";
    }

    @GetMapping("/employee/add")
    public String AddAccount(Model model) {
        EmployeeDto dto = new EmployeeDto();
        model.addAttribute("employee", dto);
        return "addEmployee";
    }

    @PostMapping("/employee/add")
    public String AddAccount(Model model, @ModelAttribute EmployeeDto dto) {
        dto.setRole("employee");
        boolean saved = service.addEmployee(dto);
        String message = (saved) ? "تم اضافه الموظف" : "حدث خطاء الرجاء المحاوله مره اخري" ;
        model.addAttribute("message", message);
        model.addAttribute("employee", new EmployeeDto());
        return "addEmployee";
    }
}
