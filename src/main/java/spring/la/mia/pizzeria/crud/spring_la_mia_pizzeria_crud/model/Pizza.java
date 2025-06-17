package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pizzas")

public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must not be null nor empty or blank")
    private String name;

    @Lob
    private String description;

    @NotBlank(message="Photo must not be null nor empty or blank")
    private String photo;

    @NotNull
    @Min(value=0, message = "Prize can not be negative")
    private Float price;

    //Offerta
    @OneToMany(mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    private List<Offerta> offerte;

    //Ingredient
    @ManyToMany
    @JoinTable(
        name = "ingredient_pizza",
        joinColumns = @JoinColumn(name="pizza_id"),
        inverseJoinColumns = @JoinColumn(name="ingredient_id")
    )

    private Set<Ingredient> ingredients;

    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Offerta> getOfferte() {
        return this.offerte;
    }

    public void setOfferte(List<Offerta> offerte) {
        this.offerte = offerte;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("%s %s", this.name, this.description);
    }

}
