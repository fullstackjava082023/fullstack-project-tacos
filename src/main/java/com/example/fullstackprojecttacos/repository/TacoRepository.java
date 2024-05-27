package com.example.fullstackprojecttacos.repository;

import com.example.fullstackprojecttacos.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
