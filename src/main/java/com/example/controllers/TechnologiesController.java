package com.example.controllers;

import com.example.Util;
import com.example.entities.dtos.TechnologyForSerDto;
import com.example.services.TechnologyService;
import com.example.utilities.results.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public  String addTechnology(@RequestBody String json, HttpServletRequest request,
                                 HttpServletResponse response){

        JSONObject requestBody = new JSONObject(json);
        TechnologyForSerDto nesne = new TechnologyForSerDto ();

        nesne.setName(requestBody.getString("name"));
        nesne.setCvId(requestBody.getInt("cvId"));

        Result result=this.technologyService.addTechnology(nesne);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @DeleteMapping("/deleteTechnology")
    public  String deleteTechnology(@RequestParam int technologyId){
        return Util.ConvertToJsonString(technologyService.deleteTechnology(technologyId));
    }

    @GetMapping("/getByCvId")
    public String getByCvId( int cvId){
        return Util.ConvertToJsonString(technologyService.getByCvId(cvId));
    }
}