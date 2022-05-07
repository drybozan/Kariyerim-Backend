package com.example.controllers;

import com.example.entities.concretes.Technology;
import com.example.entities.dtos.TechnologyForSerDto;
import com.example.services.concretes.TechnologyService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/technology")
@CrossOrigin
public class TechnologiesController {

    private static Logger logger = LoggerFactory.getLogger(TechnologiesController.class);
    private TechnologyService technologyService;

    @Autowired
    public TechnologiesController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping("/addTechnology")
    public Result addTechnology(@RequestBody TechnologyForSerDto technologyForSerDto){
        logger.info("TechnologiesController class'ı addTechnology() metodu çalıştı");
        return this.technologyService.addTechnology(technologyForSerDto);
    }

    @DeleteMapping("/deleteTechnology")
    public Result deleteTechnology(@RequestParam int technologyId){
        logger.info("TechnologiesController class'ı deleteTechnology() metodu çalıştı");
        return this.technologyService.deleteTechnology(technologyId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<Technology>> getByCvId(@RequestParam int cvId){
        logger.info("TechnologiesController class'ı getByCvId() metodu çalıştı");
        return this.technologyService.getByCvId(cvId);
    }
}