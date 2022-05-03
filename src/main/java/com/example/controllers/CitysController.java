package com.example.controllers;

import com.example.utilities.results.DataResult;
import com.example.entities.concretes.City;
import com.example.services.abstracts.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/city")
@CrossOrigin
public class CitysController {

    private CityService cityService;

    @Autowired
    public CitysController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping( value ="/getAll")
    public DataResult<List<City>> getAll(){
        return this.cityService.getAll();
    }
}
