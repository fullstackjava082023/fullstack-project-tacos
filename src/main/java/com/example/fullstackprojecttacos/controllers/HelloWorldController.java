package com.example.fullstackprojecttacos.controllers;


import com.example.fullstackprojecttacos.model.Ingredient;
import com.example.fullstackprojecttacos.model.Taco;
import com.example.fullstackprojecttacos.model.TacoOrder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.fullstackprojecttacos.model.Ingredient.Type;

import java.util.ArrayList;
import java.util.List;

import static com.example.fullstackprojecttacos.model.Ingredient.Type.*;

//localhost:8080/myapp
//@RestController
@Controller
public class HelloWorldController {

    @GetMapping(value = "/myapp", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String helloWorld() {
        Ingredient ingredient1 = new Ingredient("CHS", "Cheese", CHEESE);
        Ingredient ingredient2 = new Ingredient("CHS", "Cheese", SAUCE);
        Ingredient ingredient4 = new Ingredient("CHS", "Cheese", SAUCE);
        Ingredient ingredient3 = new Ingredient("CHS", "Cheese", VEGGIES);
        List ingredientList = List.of(ingredient3,ingredient2, ingredient1);
        Taco myTaco = new Taco(); //0
        myTaco.setIngredients(ingredientList);
        myTaco.setName("Ilia's taco");

        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.getTacos();
        tacoOrder.addTaco(myTaco);

        return myTaco.toString();

    }
}
