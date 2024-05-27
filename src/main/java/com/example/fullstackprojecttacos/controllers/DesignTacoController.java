package com.example.fullstackprojecttacos.controllers;

import com.example.fullstackprojecttacos.model.*;
import com.example.fullstackprojecttacos.repository.IngredientRepository;
import com.example.fullstackprojecttacos.repository.UserRepository;
import com.example.fullstackprojecttacos.repository.UserWalletRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.fullstackprojecttacos.model.Ingredient.Type;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//http:localhost:8080/
@Controller
@RequestMapping("/design")
@Slf4j
@SessionAttributes(names = {"tacoOrder", "mainWallet"})
public class DesignTacoController {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private UserWalletRepository userWalletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;



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
        //adding to the total order price
        double tacoPrice = taco1.calculateTotalPrice();
        tacoOrder.setOrderPrice(tacoOrder.getOrderPrice() + tacoPrice);
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

    @ModelAttribute(name = "mainWallet")
    public UserWallet addWalletToModel() {
        return userWalletRepository.findById(1L).orElse(null);
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
