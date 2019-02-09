package me.motyim.freelance.ersystem.repo;

import me.motyim.freelance.ersystem.entity.Incident;
import me.motyim.freelance.ersystem.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.stream.Stream;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
public interface IncidentRepo extends PagingAndSortingRepository<Incident,Integer> {

    Stream<Incident> findAllByUserId(User user);
}
