package com.example.fullstackprojecttacos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserWallet {

    public UserWallet(Long id) {
        this.id = id;
    }

    @Id
    private Long id;
    private double balance;
}
