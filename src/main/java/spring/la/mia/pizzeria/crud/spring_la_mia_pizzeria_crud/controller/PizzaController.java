package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Offerta;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Pizza;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
//import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.OffertaRepository;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/pizzas")


public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    // @Autowired
    // private OffertaRepository offertaRepository;

    @GetMapping
    public String index(@RequestParam(name="name", required = false) String name,  Model model) {

        List<Pizza> pizzas; //= repository.findAll();

        if(name !=null && !name.isBlank()){
            pizzas = pizzaRepository.findByNameContainingIgnoreCase(name);
        }else{
            pizzas=pizzaRepository.findAll();
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("name", name);
        return "pizzas/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model){

        Pizza pizza = pizzaRepository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "/pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        if(bindingResult.hasErrors()){
            return "pizzas/create";
        }

        pizzaRepository.save(formPizza);
        
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        if(bindingResult.hasErrors()){
            return"/pizzas/edit";
        }
        
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){

        Pizza pizza = pizzaRepository.findById(id).get();

        // for (Offerta offertaToDelete: pizza.getOfferte()) {
        //     offertaRepository.delete(offertaToDelete);
        // } Questo pezzo di codice non é più necessario se si usa il cascade nella @OneToMany in Pizza.java


        //pizzaRepository.deleteById(id); --> La query per cercare il libro e cancellarlo è già stato fatto quindi posso cancellarlo in questo modo:
        pizzaRepository.delete(pizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/offerta")
    public String offerta(@PathVariable ("id") Integer id, Model model) {
        Offerta offerta = new Offerta();
        offerta.setPizza(pizzaRepository.findById(id).get());

        model.addAttribute("offerta", offerta);
        return "offerte/create-or-edit";
    }
    
  
    
    
    
    
}
