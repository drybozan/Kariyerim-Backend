package com.example.controllers;

import com.example.entities.concretes.Employer;
import com.example.entities.dtos.EmployerForRegisterDto;
import com.example.services.concretes.EmployerService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
@CrossOrigin
public class EmployerController {

    private EmployerService employerService;

    private static Logger logger = LoggerFactory.getLogger(EmployerController.class);

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll(){
        logger.info("Employer Controller Class'ı getAll() metodu çalıştı");
        return this.employerService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EmployerForRegisterDto employerForRegisterDto){
        logger.info("Employer Controller Class'ı add() metodu çalıştı");
        Result result=this.employerService.add(employerForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getById")
    DataResult<Employer> getById(@RequestParam int id){
        logger.info("Employer Controller Class'ı getById() metodu çalıştı");
        return this.employerService.getById(id);
    }

}
