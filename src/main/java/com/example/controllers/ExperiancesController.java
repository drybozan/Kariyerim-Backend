package com.example.controllers;

import com.example.Util;
import com.example.entities.dtos.ExperianceForSetDto;
import com.example.services.ExperianceService;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    public String add(@RequestBody String json, HttpServletRequest request, HttpServletResponse response){
        JSONObject requestBody = JSONObject.fromObject(json);
        ExperianceForSetDto nesne = new ExperianceForSetDto();
        nesne.setCvId(requestBody.getInt("cvId"));
        nesne.setCompanyName(requestBody.getString("companyName"));
        nesne.setPosition(requestBody.getString("position"));
        nesne.setEndDate(Util.ConvertToDate(requestBody.getString("endDate")));
        nesne.setStartDate(Util.ConvertToDate(requestBody.getString("startDate")));
        Result result = this.experianceService.add(nesne);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @DeleteMapping(value="/delete")
    public String delete(@RequestParam int experianceId){
        logger.info("ExperiancesController class'ı delete() metodu çalıştı");
        return Util.ConvertToJsonString(this.experianceService.delete(experianceId));
    }

    @GetMapping("/getByCvId")
    public String getByCvId(@RequestParam int id){
        logger.info("ExperiancesController class'ı getByCvId() metodu çalıştı");
        return Util.ConvertToJsonString(this.experianceService.getByCvId(id));
    }
}

