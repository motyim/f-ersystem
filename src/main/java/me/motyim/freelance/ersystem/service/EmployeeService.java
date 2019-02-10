package me.motyim.freelance.ersystem.service;

import me.motyim.freelance.ersystem.entity.Employee;
import me.motyim.freelance.ersystem.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 10-Feb-19
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo ;


    public boolean login(String id, String password) {
        Optional<Employee> emp = repo.findByIdAndPassword(Integer.parseInt(id), password);
        if(!emp.isPresent())
            return false ;
        else return true;
    }
}
