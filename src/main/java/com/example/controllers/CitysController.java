package com.example.controllers;

import com.example.Util;
import com.example.services.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/city/*")
@CrossOrigin
public class CitysController {
    @Autowired

    private CityService cityService;

    private static Logger logger = LoggerFactory.getLogger(CitysController.class);


    @GetMapping( value ="/getall")
    @ResponseBody
    public String getAll(){
        logger.info("Citys Controller Class'ı getAll() metodu çalıştı");
        return Util.ConvertToJsonString(cityService.getAll());
    }
}
