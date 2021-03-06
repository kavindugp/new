package lk.group1.auth.server.repository;

import lk.group1.auth.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository  extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String name);
}
