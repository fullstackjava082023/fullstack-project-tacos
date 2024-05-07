package com.example.fullstackprojecttacos.repository;

import com.example.fullstackprojecttacos.model.Ingredient;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    //select * from ingredeints where name = name


    Ingredient findIngredientByName(String name);


    @Query("{'type' : 0}")
    List<Ingredient> findWrapIngredients();



}
