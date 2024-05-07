package com.example.fullstackprojecttacos.controllers;


import com.example.fullstackprojecttacos.model.TacoOrder;
import com.example.fullstackprojecttacos.model.UserWallet;
import com.example.fullstackprojecttacos.repository.OrderRepository;
import com.example.fullstackprojecttacos.repository.UserWalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@SessionAttributes(names = {"tacoOrder", "mainWallet"})
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserWalletRepository userWalletRepository;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    @Transactional
    public String processOrder(@ModelAttribute TacoOrder tacoOrder, SessionStatus sessionStatus,
                               @ModelAttribute(name = "mainWallet")UserWallet userWallet) {
        //need to add order processing logic

        //add money to the global wallet
        payForOrder(userWallet, tacoOrder);
        //save to database
        ///null pointer exception
        orderRepository.save(tacoOrder);
        //send emails



        log.info("Order Submitted : {} ", tacoOrder);
        //close the session
        sessionStatus.setComplete();

       return "redirect:/" ;
    }

    private void payForOrder(UserWallet userWallet, TacoOrder tacoOrder) {
        double totalOrderPrice = tacoOrder.getOrderPrice();
        double currentBalance = userWallet.getBalance();
        double newBalance = currentBalance + totalOrderPrice;
        userWallet.setBalance(newBalance);
        userWalletRepository.save(userWallet);
    }


}
