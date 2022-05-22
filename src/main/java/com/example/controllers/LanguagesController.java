package com.example.controllers;

import com.example.Util;
import com.example.entities.dtos.LanguageForSetDto;
import com.example.services.LanguageService;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/language")
@CrossOrigin
public class LanguagesController {

    private static Logger logger = LoggerFactory.getLogger(LanguagesController.class);
    private LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/addLanguage")
    public Result addLanguage(@RequestBody LanguageForSetDto languageForSetDto){
        logger.info("LanguagesController class'ı addLanguage() metodu çalıştı");
        return this.languageService.addLanguage(languageForSetDto);
    }

    @DeleteMapping("/deleteLanguage")
    public Result deleteLanguage(@RequestParam int languageId){
        logger.info("LanguagesController class'ı deleteLanguage() metodu çalıştı");
        return this.languageService.deleteLanguage(languageId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<Language>> getByCvId(@RequestParam int cvId){
        logger.info("LanguagesController class'ı getByCvId() metodu çalıştı");
        return this.languageService.getByCvId(cvId);
    }
}
