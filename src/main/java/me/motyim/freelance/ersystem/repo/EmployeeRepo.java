package me.motyim.freelance.ersystem.repo;

import me.motyim.freelance.ersystem.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
public interface EmployeeRepo extends PagingAndSortingRepository<Employee,Integer> {
}
