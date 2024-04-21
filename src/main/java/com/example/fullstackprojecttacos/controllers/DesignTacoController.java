package com.example.fullstackprojecttacos.controllers;

import com.example.fullstackprojecttacos.model.Ingredient;
import com.example.fullstackprojecttacos.model.Taco;
import com.example.fullstackprojecttacos.model.TacoOrder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
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


    public static List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );

    //Spring injects the current model
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(Model model,@ModelAttribute Taco taco1, @ModelAttribute TacoOrder tacoOrder) {
        boolean hasErrors = false;
        if (taco1 != null) {
            if (taco1.getName() == null || taco1.getName().isBlank()) {
                model.addAttribute("tacoNameError", "Name Must not be empty");
                hasErrors = true;
            }
            if (taco1.getIngredients() == null || taco1.getIngredients().isEmpty()) {
                model.addAttribute("emptyIngredientsError", "Select at least one ingredient");
                hasErrors = true;
            }
        }
        if (hasErrors) {
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
        Type[] types = Type.values();
        for (Type type : types) {//5 iteration
            List<Ingredient> ingredientsByType = filterByType(ingredients, type);
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
