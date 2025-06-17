package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Pizza;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.service.PizzaService;

@RestController
@CrossOrigin
@RequestMapping("api/pizzas")

public class PizzaRestController{

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index(){
        List<Pizza> pizzas = pizzaService.findaAll();
        return pizzas;
    }

    @GetMapping("{id}")
    public ResponseEntity<Pizza> show(@PathVariable("id") Integer id){
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);
        if(pizzaAttempt.isPresent()){
            return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Pizza>store(@Valid @RequestBody Pizza pizza){
        return new ResponseEntity<Pizza>(pizzaService.create(pizza), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Pizza> update(@Valid @RequestBody Pizza pizza, @PathVariable("id") Integer id){
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);
        if (pizzaAttempt.isPresent()) {
            return new ResponseEntity<Pizza>(pizzaService.update(pizza), HttpStatus.OK);
        }
        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id){
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);

        if (pizzaAttempt.isPresent()) {
            pizzaService.deleteById(id);
            return new ResponseEntity<Pizza>(HttpStatus.OK);
        }
        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }
    

}