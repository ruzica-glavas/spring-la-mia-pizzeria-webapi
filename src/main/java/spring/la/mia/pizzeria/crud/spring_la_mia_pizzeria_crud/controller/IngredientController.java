package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Ingredient;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Pizza;
// import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.service.IngredientService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index( Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model){

        Ingredient ingredient=ingredientService.getById(id);
        model.addAttribute("ingredient", ingredient);

        return"ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("create", true);
        return"ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient")Ingredient formIngredient, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "ingredients/create-or-edit";
        }

        ingredientService.create(formIngredient);

        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("ingredient", ingredientService.getById(id));
        model.addAttribute("edit", true);

        return "ingredients/create-or-edit";
    }

    @PostMapping("edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "ingredients/create-or-edit";
        }

        ingredientService.update(formIngredient);

        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Ingredient ingredientToDelete = ingredientService.getById(id);

        for(Pizza linkedPizza : ingredientToDelete.getPizzas()){
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientService.delete(ingredientToDelete);

        return "redirect:/ingredients";
    }

    
    
}
