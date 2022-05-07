package com.example.controllers;

import com.example.entities.concretes.School;
import com.example.entities.dtos.SchoolForSerDto;
import com.example.services.concretes.SchoolService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/school")
@CrossOrigin
public class SchoolsController {

    private static Logger logger = LoggerFactory.getLogger(SchoolsController.class);
    private SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/addSchool")
    public Result addSchool(@RequestBody SchoolForSerDto schoolForSerDto){
        logger.info("SchoolsController class'ı addSchool() metodu çalıştı");
        return this.schoolService.addSchool(schoolForSerDto);
    }

    @DeleteMapping("/deleteSchool")
    public Result deleteSchool(@RequestParam int schoolId){
        logger.info("SchoolsController class'ı deleteSchool() metodu çalıştı");
        return this.schoolService.deleteSchool(schoolId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<School>> getByCvId(@RequestParam int cvId){
        logger.info("SchoolsController class'ı getByCvId() metodu çalıştı");
        return this.schoolService.getByCvId(cvId);
    }
}
