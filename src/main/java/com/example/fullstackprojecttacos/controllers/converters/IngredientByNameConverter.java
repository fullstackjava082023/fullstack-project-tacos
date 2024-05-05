package com.example.fullstackprojecttacos.controllers.converters;

import com.example.fullstackprojecttacos.model.Ingredient;

import com.example.fullstackprojecttacos.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class IngredientByNameConverter implements Converter<String, Ingredient> {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Override
    public Ingredient convert(String ingredientName) {
        return ingredientRepository.findIngredientByName(ingredientName);
    }
}
