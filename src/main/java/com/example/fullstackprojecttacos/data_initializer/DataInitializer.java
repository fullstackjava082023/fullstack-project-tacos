package com.example.fullstackprojecttacos.data_initializer;


import com.example.fullstackprojecttacos.model.Ingredient;
import com.example.fullstackprojecttacos.model.TacoUser;
import com.example.fullstackprojecttacos.model.UserWallet;
import com.example.fullstackprojecttacos.repository.IngredientRepository;
import com.example.fullstackprojecttacos.repository.UserRepository;
import com.example.fullstackprojecttacos.repository.UserWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInitializer {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private UserWalletRepository userWalletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;


    @Bean
    public ApplicationRunner preloadData() {
        return args -> {
            ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP, 0d));
            ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP, 2d));
            ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN, 5d));
            ingredientRepository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN, 3d));
            ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES, 2d));
            ingredientRepository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES, 1d));
            ingredientRepository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE, 4d));
            ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE, 7d));
            ingredientRepository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE, 1d));
            ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE, 1d));
            //to save global wallet with ID = 1
            Optional<UserWallet> userWallet = userWalletRepository.findById(1L);
            if (userWallet.isEmpty()) {
                userWalletRepository.save(new UserWallet(1l, 0));
            }
            if (userRepository.findByUsername("admin") == null) {
                userRepository.save(new TacoUser(1l,"admin", "admin",encoder.encode("admin"),
                        "home", "home", "home", "home", "admin@gmail.com" ,"1234"));
            }

        };
    }
}
