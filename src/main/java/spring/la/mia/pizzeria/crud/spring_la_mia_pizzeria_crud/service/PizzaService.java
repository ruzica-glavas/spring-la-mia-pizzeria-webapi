package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Offerta;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Pizza;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.OffertaRepository;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OffertaRepository offertaRepository;

    public List<Pizza> findAll(String name){
        if(name !=null && !name.isBlank()){
           return pizzaRepository.findByNameContainingIgnoreCase(name);
        }else{
           return pizzaRepository.findAll();
        }
    }

    public Pizza getById(Integer id){
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isEmpty()) {
            throw new IllegalArgumentException("Pizza not found with id:" + id);
        }
        return pizza.get();
    }

    public Optional<Pizza> findById(Integer id){
        return pizzaRepository.findById(id);
    }

    public Pizza create(Pizza pizza){
        return pizzaRepository.save(pizza);
        //Si possono prevedere comportamenti diversi in base se é una create o un update. In questo caso si può aggiornare solo alcuni campi a seguito della creazione.
    }

    public Pizza update(Pizza pizza){
        return pizzaRepository.save(pizza);
        //aggiornamento di altri campi a seguito dell'aggiornamento
    }

    public void deleteById(Integer id){
        pizzaRepository.deleteById(id);
    }

    public void delete(Pizza pizza){
        for(Offerta offertaToDelete:pizza.getOfferte()){
            offertaRepository.delete(offertaToDelete);
        }
        pizzaRepository.delete(pizza);
    }

    public boolean exists(Pizza pizza){
        return pizzaRepository.existsById(pizza.getId());
    }

    public boolean existsById(Integer id){
        return pizzaRepository.existsById(id);
    }
       
    


}
