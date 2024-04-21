package com.example.fullstackprojecttacos.controllers.converters;

import com.example.fullstackprojecttacos.model.Ingredient;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.example.fullstackprojecttacos.controllers.DesignTacoController.ingredients;

@Component
public class IngredientByNameConverter implements Converter<String, Ingredient> {

    @Override
    public Ingredient convert(String ingredientName) {
        //carnitas -> Ingredient(carnitax, Wrap, FLKO)
        return ingredients.stream()
                .filter(x -> x.getName().equals(ingredientName))
                .findFirst()
                .orElse(null);
    }
}
