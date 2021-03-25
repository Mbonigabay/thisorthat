package rw.thisorthat.thisorthat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rw.thisorthat.thisorthat.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
    User findByUserId(String userId);
}
