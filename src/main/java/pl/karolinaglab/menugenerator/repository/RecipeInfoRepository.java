package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.model.RecipeInfo;

import java.util.List;

@Repository
public interface RecipeInfoRepository extends JpaRepository<RecipeInfo, Integer> {

    List<RecipeInfo> findAllByMenu_MenuId(int id);
    List<RecipeInfo> findAllByRecipeId(int id);
}
