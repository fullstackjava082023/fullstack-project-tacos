package com.example.fullstackprojecttacos.controllers;

import com.example.fullstackprojecttacos.model.Ingredient;
import com.example.fullstackprojecttacos.model.Taco;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.example.fullstackprojecttacos.model.Ingredient.Type;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//http:localhost:8080/
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    //model
//    {
//         "wrap" : new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                //            new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//          "protein:         new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                //            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//          "veggies"     :    new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                //            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//          "cheese":          list(new Ingredient("CHED", "Cheddar", Type.CHEESE),
                //            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),)
//            "sauce":         new Ingredient("SLSA", "Salsa", Type.SAUCE),
                //            new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    //    }

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
    public String showDesignForm(Model model) {
        Type[] types = Type.values();
        for (Type type : types) {//5 iteration
            List<Ingredient> ingredientsByType = filterByType(ingredients, type);
            model.addAttribute(type.toString().toLowerCase(), ingredientsByType);
        }
//        model.addAttribute("ingredients", ingredients );
        return "design";
    }

    @PostMapping
    @ResponseBody
    public String processTaco(Model model, HttpServletRequest request) {
        Taco taco1 = new Taco();
        String name = request.getParameter("tacoName");
        taco1.setName(name);
        // Retrieve selected ingredients
        String[] selectedIngredients = request.getParameterValues("selectedIngredients");
//        List<Ingredient> ingredientsForTaco = getIngredientsByNames(ingredients,selectedIngredients);
//        taco1.setIngredients(ingredientsForTaco);
        return "we created taco with name " + taco1.getName()  +
                " and ingredients: " + selectedIngredients;
    }






    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
