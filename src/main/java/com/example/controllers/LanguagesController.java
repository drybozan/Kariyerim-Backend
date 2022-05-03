package com.example.controllers;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Language;
import com.example.entities.dtos.LanguageForSetDto;
import com.example.services.abstracts.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



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
    public Result addLanguage(@RequestBody LanguageForSetDto languageForSetDto){
        return this.languageService.addLanguage(languageForSetDto);
    }

    @DeleteMapping("/deleteLanguage")
    public Result deleteLanguage(@RequestParam int languageId){
        return this.languageService.deleteLanguage(languageId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<Language>> getByCvId(@RequestParam int cvId){
        return this.languageService.getByCvId(cvId);
    }
}
