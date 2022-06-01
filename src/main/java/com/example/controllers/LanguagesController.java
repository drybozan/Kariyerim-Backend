package com.example.controllers;

import com.example.Util;
import com.example.entities.dtos.LanguageForSetDto;
import com.example.services.LanguageService;
import com.example.utilities.results.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/language")
@CrossOrigin
public class LanguagesController {

    private LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/addLanguage")
    public String addLanguage(@RequestBody String json, HttpServletRequest request,
                              HttpServletResponse response){
        JSONObject requestBody = new JSONObject(json);
        LanguageForSetDto nesne = new LanguageForSetDto();
        nesne.setName(requestBody.getString("name"));
        nesne.setLevel(Integer.toString(requestBody.getInt("level")));
        nesne.setCvId(requestBody.getInt("cvId"));
        Result result=this.languageService.addLanguage(nesne);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @DeleteMapping("/deleteLanguage")
    public String deleteLanguage(@RequestParam int languageId){
        return Util.ConvertToJsonString(languageService.deleteLanguage(languageId));
    }

    @GetMapping("/getByCvId")
    public String getByCvId(@RequestParam int cvId){
        return Util.ConvertToJsonString(languageService.getByCvId(cvId));
    }
}
