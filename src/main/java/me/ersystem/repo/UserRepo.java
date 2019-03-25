package me.ersystem.repo;

import me.ersystem.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @since 09-Feb-19
 */
@Repository
public interface UserRepo extends PagingAndSortingRepository<User , Integer> {

    Optional<User> findOneByUsernameAndPhoneNumber(String username , String phoneNumber);
}
