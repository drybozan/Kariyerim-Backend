package com.example.controllers;

import com.example.Util;
import com.example.entities.dtos.SchoolForSerDto;
import com.example.services.SchoolService;
import com.example.utilities.results.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String addSchool(@RequestBody String json, HttpServletRequest request, HttpServletResponse response){
        JSONObject requestBody = new JSONObject(json);
        SchoolForSerDto nesne = new SchoolForSerDto();
        nesne.setName(requestBody.getString("name"));
        nesne.setDepartment(requestBody.getString("department"));
        nesne.setEndDate(Util.ConvertToDate(requestBody.getString("endDate")));
        nesne.setStartDate(Util.ConvertToDate(requestBody.getString("startDate")));
        nesne.setCvId(requestBody.getInt("cvId"));
        Result result=this.schoolService.addSchool(nesne);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @DeleteMapping("/deleteSchool")
    public String deleteSchool(@RequestParam int schoolId){
        return Util.ConvertToJsonString(schoolService.deleteSchool(schoolId));
    }

    @GetMapping("/getByCvId")
    public String getByCvId(@RequestParam int cvId){
        return Util.ConvertToJsonString(
                schoolService.getByCvId(cvId));
    }
}
