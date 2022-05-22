package com.example.controllers;

import com.example.Util;
import com.example.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
