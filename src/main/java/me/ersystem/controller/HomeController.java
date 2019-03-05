package me.ersystem.controller;

import me.ersystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 10-Feb-19
 */
@Controller
public class HomeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/home")
    public String doLogin(@RequestParam(defaultValue = "0") String id,@RequestParam String password, Model model) {
       if( !service.login(id,password)){
           model.addAttribute("message","من فضلك ادخل المعلومات الصحيحه");
           return "login";
       }
        return "home";
    }

    @GetMapping("/home")
    public String homepage() {
        return "home";
    }
}
