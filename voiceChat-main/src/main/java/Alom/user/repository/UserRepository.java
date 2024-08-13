package Alom.user.repository;

import Alom.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserUuid(UUID userUuid);

    boolean existsById(String userId);
}
