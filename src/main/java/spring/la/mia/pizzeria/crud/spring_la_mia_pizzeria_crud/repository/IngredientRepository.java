package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
    
} 
