package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.enumTypes.RoleName;
import pl.karolinaglab.menugenerator.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName roleUser);
}
