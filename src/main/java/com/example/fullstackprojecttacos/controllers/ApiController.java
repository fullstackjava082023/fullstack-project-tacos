package com.example.fullstackprojecttacos.controllers;

import com.example.fullstackprojecttacos.model.Taco;
import com.example.fullstackprojecttacos.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private TacoRepository tacoRepo;

    @GetMapping("/tacos")
    public List getTacos() {
        return (List) tacoRepo.findAll();
    }



    @GetMapping("/tacos/{id}")
    public ResponseEntity tacoById(@PathVariable("id")  Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity(optTaco.get(), HttpStatus.OK);
        }
        //no toca:
        return new ResponseEntity("Taco not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/tacos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteTacoById(@PathVariable("id")  Long id) {
        Optional<Taco> tacoFromDB = tacoRepo.findById(id);
        tacoRepo.delete(tacoFromDB.get());
        return new ResponseEntity("Taco deleted", HttpStatus.OK);
    }


    //when recieved /api/tacos POST request, create a taco
    @PostMapping("/tacos")
    public String createTaco(@RequestBody Taco taco) {
        tacoRepo.save(taco);
        return "Taco saved";
    }










}
