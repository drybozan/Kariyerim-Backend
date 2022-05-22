package com.example.controllers;

import com.example.Util;
import com.example.entities.concretes.JobPosition;
import com.example.services.JobPositionService;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/api/jobpositions")
@CrossOrigin
public class JobPositionController {

    private JobPositionService jobPositionService;

    private static Logger logger = LoggerFactory.getLogger(JobPositionController.class);

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobPosition>> getAll(){
        logger.info("JobPositionController class'ı getAll() metodu çalıştı");
        return this.jobPositionService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobPosition jobPosition){
        logger.info("JobPositionController class'ı add() metodu çalıştı");
        Result result=this.jobPositionService.add(jobPosition);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
