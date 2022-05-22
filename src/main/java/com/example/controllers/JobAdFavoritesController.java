package com.example.controllers;

import com.example.Util;
import com.example.entities.concretes.JobAdFavorites;
import com.example.services.JobAdFavoritesService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobAdFavorites")
@CrossOrigin
public class JobAdFavoritesController {
    private static Logger logger = LoggerFactory.getLogger(JobAdFavoritesController.class);

    private JobAdFavoritesService jobAdFavoritesService;

    @Autowired
    public JobAdFavoritesController(JobAdFavoritesService jobAdFavoritesService) {
        this.jobAdFavoritesService = jobAdFavoritesService;
    }

    @GetMapping("/getByCandidateId")
    public String getByCandidateId(@RequestParam int candidateId){
        logger.info("JobAdFavoritesController class'ı getByCandidateId() metodu çalıştı");
        DataResult<List<JobAdFavorites>> result=this.jobAdFavoritesService.getByCandidateId(candidateId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @PostMapping("/addFavorite")
    public String addFavorite(@RequestParam int candidateId,@RequestParam int jobAdId){
        logger.info("JobAdFavoritesController class'ı addFavorite() metodu çalıştı");
        Result result=this.jobAdFavoritesService.addFavorite(candidateId,jobAdId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @DeleteMapping("/removeFavorite")
    public String removeFavorite(@RequestParam int favoriteId){
        logger.info("JobAdFavoritesController class'ı removeFavorite() metodu çalıştı");
        Result result = this.jobAdFavoritesService.removeFavorite(favoriteId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));//
    }
}
