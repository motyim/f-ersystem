package me.ersystem.repo;

import me.ersystem.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @since 09-Feb-19
 */
@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee,Integer> {

    Optional<Employee> findByIdAndPassword(Integer id,String password);
}
