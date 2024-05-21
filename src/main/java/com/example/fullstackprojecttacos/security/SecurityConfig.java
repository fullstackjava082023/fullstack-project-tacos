package com.example.fullstackprojecttacos.security;

import com.example.fullstackprojecttacos.model.TacoUser;
import com.example.fullstackprojecttacos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {



    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                .requestMatchers("/design", "/orders/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "OIDC_USER", "OAUTH2_USER")
                .requestMatchers("/adminPanel").hasRole("ADMIN")
                .requestMatchers("/", "/**").permitAll())
                .oauth2Login(login -> login.loginPage("/login")) // spring gives default link
                .formLogin(login -> login.loginPage("/login"))
                .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        List<UserDetails> usersList = new ArrayList<>();
//        usersList.add(new User(
//                "Arja", encoder.encode("1234"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        usersList.add(new User(
//                "John", encoder.encode("admin"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        usersList.add(new User(
//                "Ned", encoder.encode("1234"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//
//        return new InMemoryUserDetailsManager(usersList);
//    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return username -> {
            TacoUser user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }


//    public UserDetails getUserFromDB(String username) {
//        return userRepository.findByUsername(username);
//    }








}
