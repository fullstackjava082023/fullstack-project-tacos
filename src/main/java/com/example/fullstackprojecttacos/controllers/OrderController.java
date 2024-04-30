package com.example.fullstackprojecttacos.controllers;


import com.example.fullstackprojecttacos.model.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/orders")
@Slf4j
public class OrderController {


    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@ModelAttribute TacoOrder tacoOrder, SessionStatus sessionStatus) {
        //need to add order processing logic
        //save to database
        //send emails
        //close the session

        log.info("Order Submitted : {} ", tacoOrder);
        sessionStatus.setComplete();

       return "redirect:/" ;
    }


}
