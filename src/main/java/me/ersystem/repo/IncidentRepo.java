package me.ersystem.repo;

import me.ersystem.entity.Incident;
import me.ersystem.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Repository
public interface IncidentRepo extends PagingAndSortingRepository<Incident,Integer> {

    Stream<Incident> findAllByUserId(User user);

    Stream<Incident> findAllByStatus(String status);
}
