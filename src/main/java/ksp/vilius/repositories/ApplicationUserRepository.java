package ksp.vilius.repositories;

import ksp.vilius.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByEmail(String email);

    ApplicationUser getById(Long id);

    ApplicationUser findByUsername(String username);
}
