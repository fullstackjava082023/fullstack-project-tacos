package com.example.fullstackprojecttacos.repository;

import com.example.fullstackprojecttacos.model.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    //select * from ingredeints where name = name

    @Query("select o from Ingredient o where o.name = ?1")
    Ingredient findIngredientByName(String name);


    @Query(value = "select * from ingredient where type = 0", nativeQuery = true)
    List<Ingredient> findWrapIngredients();



}
