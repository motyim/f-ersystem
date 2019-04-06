package me.ersystem.repo;

import me.ersystem.dto.IncidentTypeStatDto;
import me.ersystem.dto.LocationStatDto;
import me.ersystem.entity.Incident;
import me.ersystem.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @since 09-Feb-19
 */
@Repository
public interface IncidentRepo extends PagingAndSortingRepository<Incident,Integer> {

    Stream<Incident> findAllByUserId(User user);

    Stream<Incident> findAllByStatusInOrderByStatus(Collection<String> status);

    @Query("SELECT " +
            "    new me.ersystem.dto.LocationStatDto(I.region, COUNT(I)) " +
            "FROM " +
            "    Incident I " +
            "GROUP BY " +
            "    I.region")
    List<LocationStatDto> findLocationCount();

    @Query("SELECT " +
            "    new me.ersystem.dto.IncidentTypeStatDto(I.type, COUNT(I)) " +
            "FROM " +
            "    Incident I " +
            "GROUP BY " +
            "    I.type")
    List<IncidentTypeStatDto> findTypeCount();
}
