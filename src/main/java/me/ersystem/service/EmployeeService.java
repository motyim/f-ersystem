package me.ersystem.service;

import me.ersystem.dto.EmployeeDto;
import me.ersystem.entity.Employee;
import me.ersystem.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
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

    public List<EmployeeDto> getAllAccounts() {
        Iterable<Employee> employees = repo.findAll();
        Type listType = new TypeToken<List<EmployeeDto>>() {}.getType();
        List<EmployeeDto> employeeDtos = mapper.map(employees, listType);
        return employeeDtos;
    }

    public boolean addEmployee(EmployeeDto dto) {
        Employee employee = mapper.map(dto, Employee.class);
        Employee saved = repo.save(employee);
        return saved.getId() != null  && saved.getId() > 0 ;
    }
}
