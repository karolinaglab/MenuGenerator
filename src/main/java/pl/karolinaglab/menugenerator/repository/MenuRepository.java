package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.model.Menu;

import java.util.Date;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findAllByUser_Id(int id);
}
