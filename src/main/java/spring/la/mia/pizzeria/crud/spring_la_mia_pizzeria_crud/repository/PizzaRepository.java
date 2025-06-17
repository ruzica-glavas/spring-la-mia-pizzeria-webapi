package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{ 
        public List<Pizza> findByNameContainingIgnoreCase(String name);
} 