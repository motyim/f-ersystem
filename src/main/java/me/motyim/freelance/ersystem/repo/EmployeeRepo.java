package me.motyim.freelance.ersystem.repo;

import me.motyim.freelance.ersystem.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee,Integer> {

    Optional<Employee> findByIdAndPassword(Integer id,String password);
}
