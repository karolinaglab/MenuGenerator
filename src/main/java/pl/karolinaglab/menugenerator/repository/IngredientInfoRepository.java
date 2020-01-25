package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.model.IngredientInfo;

import java.util.List;

@Repository
public interface IngredientInfoRepository extends JpaRepository<IngredientInfo, Integer> {

    List<IngredientInfo> findAllByIngredientId(int id);
    List<IngredientInfo> findAllByRecipeId(int id);

}
