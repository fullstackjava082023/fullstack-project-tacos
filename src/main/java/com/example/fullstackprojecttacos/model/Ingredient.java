package com.example.fullstackprojecttacos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;
    private double price;


    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }



}
