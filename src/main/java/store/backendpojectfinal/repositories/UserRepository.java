package store.backendpojectfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import store.backendpojectfinal.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
