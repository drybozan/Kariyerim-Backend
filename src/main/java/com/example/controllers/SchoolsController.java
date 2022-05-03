package com.example.controllers;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.School;
import com.example.entities.dtos.SchoolForSerDto;
import com.example.services.abstracts.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/school")
@CrossOrigin
public class SchoolsController {

    private SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/addSchool")
    public Result addSchool(@RequestBody SchoolForSerDto schoolForSerDto){
        return this.schoolService.addSchool(schoolForSerDto);
    }

    @DeleteMapping("/deleteSchool")
    public Result deleteSchool(@RequestParam int schoolId){
        return this.schoolService.deleteSchool(schoolId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<School>> getByCvId(@RequestParam int cvId){
        return this.schoolService.getByCvId(cvId);
    }
}
