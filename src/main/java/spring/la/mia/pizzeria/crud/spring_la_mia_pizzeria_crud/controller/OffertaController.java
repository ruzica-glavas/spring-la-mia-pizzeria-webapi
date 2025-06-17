package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Offerta;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.OffertaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/offerte")

public class OffertaController {

    @Autowired
    private OffertaRepository offertaRepository;



    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "offerte/create-or-edit";
        }
        
        offertaRepository.save(formOfferta);
        
        return "redirect:/pizzas";
    }

    //Metodo che resituisce una edit da compilare (con già i dati inseriti)

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("offerta", offertaRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "offerte/create-or-edit";
    };

    //Metodo che effettua una update vera e propria con validazione

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "offerte/create-or-edit";
        }

        offertaRepository.save(formOfferta);

        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    };

    
}
