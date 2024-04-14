package com.example.fullstackprojecttacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//http:localhost:8080/
@Controller
@RequestMapping("/design")
public class DesignTacoController {



    @GetMapping
    public String showDesignForm(){
        return "design";
    }
}
