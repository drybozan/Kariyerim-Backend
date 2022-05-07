package com.example.controllers;

import com.example.entities.concretes.Cv;
import com.example.services.concretes.CvService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api/cv")
@CrossOrigin
public class CvsController {

    private static Logger logger = LoggerFactory.getLogger(CvsController.class);

    private CvService cvService;

    @Autowired
    public CvsController(CvService cvService) {
        this.cvService = cvService;
    }

   

    @GetMapping("/getall")
    public DataResult<List<Cv>> getAll(){

        logger.info("CvsController class'ı getAll() metodu çalıştı");
        return this.cvService.getAll();
    }

    @GetMapping("/getByCvId")
    public ResponseEntity<?> getByCvId(@RequestParam int cvId){
        DataResult<Cv> result=this.cvService.getByCvId(cvId);
        if(result.isSuccess()){
            logger.info("CvsController class'ı getByCvId() metodu çalıştı");
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getByCandidateId")
    public ResponseEntity<?> getByCandidateId(@RequestParam int candidateId){
        logger.info("CvsController class'ı getByCandidateId() metodu çalıştı");
        DataResult<Cv> result=this.cvService.getByCandidateId(candidateId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateGithub")
    public ResponseEntity<?> updateGithub(@RequestParam String githublink,@RequestParam int cvId){
        logger.info("CvsController class'ı updateGithub() metodu çalıştı");
        Result result=this.cvService.updateGithub(githublink,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteGithub")
    public ResponseEntity<?> deleteGithub(@RequestParam int cvId){
        logger.info("CvsController class'ı deleteGithub() metodu çalıştı");
        Result result=this.cvService.deleteGithub(cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateLinkedin")
    public ResponseEntity<?> updateLinkedin(@RequestParam String linkedinlink,@RequestParam int cvId){
        logger.info("CvsController class'ı updateLinkedin() metodu çalıştı");
        Result result=this.cvService.updateLinkedin(linkedinlink,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteLinkedin")
    public ResponseEntity<?> deleteLinkedin(@RequestParam int cvId){
        logger.info("CvsController class'ı deleteLinkedin() metodu çalıştı");
        Result result=this.cvService.deleteLinkedin(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateBiography")
    public ResponseEntity<?> updateBiography(@RequestParam String biography,@RequestParam int cvId){
        logger.info("CvsController class'ı updateBiography() metodu çalıştı");
        Result result=this.cvService.updateBiography(biography,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteBiography")
    public ResponseEntity<?> deleteBiography(@RequestParam int cvId){
        logger.info("CvsController class'ı deleteBiography() metodu çalıştı");
        Result result=this.cvService.deleteBiography(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}

