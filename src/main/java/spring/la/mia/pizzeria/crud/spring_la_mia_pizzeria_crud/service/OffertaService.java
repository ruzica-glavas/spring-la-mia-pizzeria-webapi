package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Offerta;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.OffertaRepository;


@Service
public class OffertaService {
    @Autowired
    private OffertaRepository offertaRepository;

    public Offerta create(Offerta offerta){
        return offertaRepository.save(offerta);
    }

    public Offerta update(Offerta offerta){
        return offertaRepository.save(offerta);
    }

    public Offerta getById(Integer id){
        return offertaRepository.findById(id).get();
    }

    public void deleteById(Integer id){
        offertaRepository.deleteById(id);
    }
}
