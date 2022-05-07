package com.example.controllers;

import com.example.entities.concretes.Candidate;
import com.example.entities.dtos.CandidateForRegisterDto;
import com.example.services.concretes.CandidateService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;

    private static Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping(value="/getall")
    public DataResult<List<Candidate>> getAll(){
        logger.info("CandidateController class'ı getAll() metodu çalıştı");
        return candidateService.getAll();
    }

    @PostMapping(value="/add")
    public ResponseEntity<?> add(@RequestBody CandidateForRegisterDto candidateForRegisterDto){
        logger.info("CandidateController class'ı add() metodu çalıştı");
        Result result=this.candidateService.add(candidateForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

 
}

