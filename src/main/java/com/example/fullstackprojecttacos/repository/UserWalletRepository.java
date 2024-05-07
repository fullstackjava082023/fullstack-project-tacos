package com.example.fullstackprojecttacos.repository;

import com.example.fullstackprojecttacos.model.TacoOrder;
import com.example.fullstackprojecttacos.model.UserWallet;
import org.springframework.data.repository.CrudRepository;

public interface UserWalletRepository extends CrudRepository<UserWallet, Long> {
}
