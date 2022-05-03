package com.example.controllers;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Technology;
import com.example.entities.dtos.TechnologyForSerDto;
import com.example.services.abstracts.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/technology")
@CrossOrigin
public class TechnologiesController {

    private TechnologyService technologyService;

    @Autowired
    public TechnologiesController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping("/addTechnology")
    public Result addTechnology(@RequestBody TechnologyForSerDto technologyForSerDto){
        return this.technologyService.addTechnology(technologyForSerDto);
    }

    @DeleteMapping("/deleteTechnology")
    public Result deleteTechnology(@RequestParam int technologyId){
        return this.technologyService.deleteTechnology(technologyId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<Technology>> getByCvId(@RequestParam int cvId){
        return this.technologyService.getByCvId(cvId);
    }
}