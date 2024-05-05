package com.example.fullstackprojecttacos.repository;

import com.example.fullstackprojecttacos.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
