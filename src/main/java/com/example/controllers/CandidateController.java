package com.example.controllers;

import com.example.Util;
import com.example.entities.dtos.CandidateForRegisterDto;
import com.example.services.CandidateService;
import com.example.utilities.results.Result;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    private static Logger logger = LoggerFactory.getLogger(CandidateController.class);


    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping(value="/getall")
    public String getAll(){
        logger.info("CandidateController class'ı getAll() metodu çalıştı");
        return Util.ConvertToJsonString(candidateService.getAll());
    }

    @PostMapping(value="/add")
    public String add(@RequestBody String json, HttpServletRequest request, HttpServletResponse response){
        JSONObject requestBody = JSONObject.fromObject(json);

        logger.info(requestBody +  " XXXXXXXXXXXXX ");
        CandidateForRegisterDto nesne = new CandidateForRegisterDto();
        nesne.setFirstName(requestBody.getString("firstName"));
        nesne.setLastName(requestBody.getString("lastName"));
        nesne.setPassword(requestBody.getString("password"));
        nesne.setNationalNumber(requestBody.getString("nationalNumber"));
        nesne.setRePassword(requestBody.getString("rePassword"));
        nesne.setEmail(requestBody.getString("email"));
        nesne.setBirthDate(Util.ConvertToDate(requestBody.getString("birthDate")));
        Result result=this.candidateService.add(nesne);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

 
}

