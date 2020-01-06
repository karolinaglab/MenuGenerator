package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
