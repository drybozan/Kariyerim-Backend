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

    @GetMapping( value ="/getall")
    @ResponseBody
    public String getAll(){
        return Util.ConvertToJsonString(cityService.getAll());
    }
}
