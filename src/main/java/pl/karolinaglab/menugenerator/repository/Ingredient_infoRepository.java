package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.model.Ingredient_info;

@Repository
public interface Ingredient_infoRepository extends JpaRepository<Ingredient_info, Integer> {

}
