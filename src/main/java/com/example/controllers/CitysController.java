package com.example.controllers;

import com.example.entities.concretes.City;
import com.example.services.concretes.CityService;
import com.example.utilities.results.DataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
@CrossOrigin
public class CitysController {

    private CityService cityService;

    private static Logger logger = LoggerFactory.getLogger(CitysController.class);

    @Autowired
    public CitysController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping( value ="/getAll")
    public DataResult<List<City>> getAll(){
        logger.info("Citys Controller Class'ı getAll() metodu çalıştı");
        return this.cityService.getAll();
    }
}
