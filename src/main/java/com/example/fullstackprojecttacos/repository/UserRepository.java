package com.example.fullstackprojecttacos.repository;

import com.example.fullstackprojecttacos.model.TacoUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<TacoUser, Long> {

    TacoUser findByUsername(String username);


}
