package me.motyim.freelance.ersystem.service;

import me.motyim.freelance.ersystem.dto.EmployeeDto;
import me.motyim.freelance.ersystem.entity.Employee;
import me.motyim.freelance.ersystem.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper mapper;

    public boolean login(String id, String password) {
        Optional<Employee> emp = repo.findByIdAndPassword(Integer.parseInt(id), password);
        if(!emp.isPresent())
            return false ;
        else return true;
    }

    public EmployeeDto findAccount(int id) {
        Optional<Employee> empOpt = repo.findById(id);

        if(!empOpt.isPresent())
           throw new RuntimeException("employee not found");

        Employee employee = empOpt.get();
        EmployeeDto dto = mapper.map(employee, EmployeeDto.class);
        return dto;

    }

    public void updateEmployee(EmployeeDto dto) {
        Employee emp = mapper.map(dto, Employee.class);
        repo.save(emp);
    }
}
