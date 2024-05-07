package com.example.fullstackprojecttacos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Taco {

    private static double TACO_BASE_PRICE = 20d;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The Taco name must not be empty")
    private String name;


    @NotNull
    @ManyToMany
    @Size(min = 1, message = "Select at least one ingredient")
    private List<Ingredient> ingredients;


    /***
     * Calculates total price of taco
     * @return total price of taco
     */
    public double calculateTotalPrice() {
        double totalPrice = TACO_BASE_PRICE;
        for (Ingredient ingredient : ingredients) {
            totalPrice += ingredient.getPrice();
        }
        return totalPrice;
    }
}
