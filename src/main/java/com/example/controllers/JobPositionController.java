package com.example.controllers;

import com.example.Util;
import com.example.entities.concretes.JobPosition;
import com.example.services.JobPositionService;
import com.example.utilities.results.Result;
import org.json.JSONObject;
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

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getall")
    public String getAll(){
        return Util.ConvertToJsonString(jobPositionService.getAll());
    }


    @PostMapping("/add")
    public String add(@RequestBody String json, HttpServletRequest request, HttpServletResponse response){
        JSONObject requestBody = new JSONObject(json);
        JobPosition nesne = new JobPosition();
        nesne.setName(requestBody.getString("name"));
        Result result=this.jobPositionService.add(nesne);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }
}
