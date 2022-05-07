package com.example.controllers;

import com.example.entities.concretes.Experiance;
import com.example.entities.dtos.ExperianceForSetDto;
import com.example.services.concretes.ExperianceService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/experiances")
@CrossOrigin
public class ExperiancesController {
    private static Logger logger = LoggerFactory.getLogger(ExperiancesController.class);

    private ExperianceService experianceService;

    @Autowired
    public ExperiancesController(ExperianceService experianceService) {
        this.experianceService = experianceService;
    }

    @PostMapping(value="/add")
    public ResponseEntity<?> add(@RequestBody ExperianceForSetDto experianceForSetDto){
        logger.info("ExperiancesController class'ı add() metodu çalıştı");
        Result result = this.experianceService.add(experianceForSetDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping(value="/delete")
    public Result delete(@RequestParam int experianceId){
        logger.info("ExperiancesController class'ı delete() metodu çalıştı");
        return this.experianceService.delete(experianceId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<Experiance>> getByCvId(@RequestParam int id){
        logger.info("ExperiancesController class'ı getByCvId() metodu çalıştı");
        return this.experianceService.getByCvId(id);
    }
}

