package com.example.controllers;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Candidate;
import com.example.entities.dtos.CandidateForRegisterDto;
import com.example.services.abstracts.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping(value="/getall")
    public DataResult<List<Candidate>> getAll(){
        return candidateService.getAll();
    }

    @PostMapping(value="/add")
    public ResponseEntity<?> add(@RequestBody CandidateForRegisterDto candidateForRegisterDto){
        Result result=this.candidateService.add(candidateForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

 
}

