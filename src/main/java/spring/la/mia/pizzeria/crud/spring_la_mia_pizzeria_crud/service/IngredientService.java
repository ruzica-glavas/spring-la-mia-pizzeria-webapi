package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Ingredient;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.IngredientRepository;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }

    public Ingredient getById(Integer id){
        return ingredientRepository.findById(id).get();
    }

    public Boolean existsById(Integer id){
        return ingredientRepository.existsById(id);
    }

    public Ingredient create(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public void delete(Ingredient ingredient){
        ingredientRepository.delete(ingredient);
    }

    public void deleteById(Integer id){
        ingredientRepository.deleteById(id);
    }
}
