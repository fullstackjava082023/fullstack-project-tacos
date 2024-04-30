package com.example.fullstackprojecttacos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {

    @NotBlank(message = "The Taco name must not be empty")
    private String name;


    @NotNull
    @Size(min = 1, message = "Select at least one ingredient")
    private List<Ingredient> ingredients;
}
