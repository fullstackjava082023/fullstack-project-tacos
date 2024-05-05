package com.example.fullstackprojecttacos.controllers;

import com.example.fullstackprojecttacos.model.Ingredient;
import com.example.fullstackprojecttacos.model.Taco;
import com.example.fullstackprojecttacos.model.TacoOrder;
import com.example.fullstackprojecttacos.repository.IngredientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.fullstackprojecttacos.model.Ingredient.Type;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//http:localhost:8080/
@Controller
@RequestMapping("/design")
@Slf4j
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @Autowired
    private IngredientRepository ingredientRepository;


    @Bean
    public ApplicationRunner preloadData() {
        return args -> {
            ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        };
    }

    //Spring injects the current model
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco1, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "/design";
        }
        //adding new taco after submit
        tacoOrder.addTaco(taco1);
        log.info("Taco submitted: {}", taco1);
        return "redirect:/orders/current";
    }

    @ModelAttribute
    public Taco addTacoToModel() {
        return new Taco();
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder addOrderToModel() {
        return new TacoOrder();
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredientList = (List<Ingredient>) ingredientRepository.findAll();
        Type[] types = Type.values();
        for (Type type : types) {//5 iteration
            List<Ingredient> ingredientsByType = filterByType(ingredientList, type);
            model.addAttribute(type.toString().toLowerCase(), ingredientsByType);
        }
    }




//    private List<Ingredient> getIngredientsByNames(String[] selectedIngredients) {
//        List<Ingredient> resultList = new ArrayList<>();
//        for (String ingredientName : selectedIngredients) {
//            for (Ingredient ingredient : ingredients) {
//                if (ingredient.getName().equals(ingredientName)) {
//                    resultList.add(ingredient);
//                }
//            }
//        }
//        return resultList;
//    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
