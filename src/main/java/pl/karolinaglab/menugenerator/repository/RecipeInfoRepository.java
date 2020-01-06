package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.model.RecipeInfo;

@Repository
public interface RecipeInfoRepository extends JpaRepository<RecipeInfo, Integer> {
}
