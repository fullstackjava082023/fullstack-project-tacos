package com.example.fullstackprojecttacos.controllers;


import com.example.fullstackprojecttacos.model.TacoOrder;
import com.example.fullstackprojecttacos.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@ModelAttribute TacoOrder tacoOrder, SessionStatus sessionStatus) {
        //need to add order processing logic
        //save to database
        orderRepository.save(tacoOrder);
        //send emails



        log.info("Order Submitted : {} ", tacoOrder);
        //close the session
        sessionStatus.setComplete();

       return "redirect:/" ;
    }


}
