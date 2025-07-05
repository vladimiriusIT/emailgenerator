package people.spheres.emailgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import people.spheres.emailgenerator.entity.AppUser;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByUsername(String username);
}
