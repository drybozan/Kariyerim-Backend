package com.example.controllers;

import com.example.Util;
import com.example.entities.concretes.Cv;
import com.example.services.CvService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cv")
@CrossOrigin
public class CvsController {

    private CvService cvService;

    @Autowired
    public CvsController(CvService cvService) {
        this.cvService = cvService;
    }

   

    @GetMapping("/getall")
    public String getAll(){
        DataResult<List<Cv>> res = this.cvService.getAll();
       // res.getData().get(0).getCandidate().setDateOfBirth(null);
        return Util.ConvertToJsonString(new SuccessDataResult<List<Cv>>(res.getData(),"Data listelendi"));
    }

    @GetMapping("/getByCvId")
    public String getByCvId(@RequestParam int cvId){
        DataResult<Cv> result=this.cvService.getByCvId(cvId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @GetMapping("/getByCandidateId")
    public String getByCandidateId(@RequestParam int candidateId){
        DataResult<Cv> result=this.cvService.getByCandidateId(candidateId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @PutMapping("/updateGithub")
    public String updateGithub(@RequestParam String githublink,@RequestParam int cvId){
        Result result=this.cvService.updateGithub(githublink,cvId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @DeleteMapping("/deleteGithub")
    public String deleteGithub(@RequestParam int cvId){
        Result result=this.cvService.deleteGithub(cvId);
        if (result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }
    @PutMapping("/updateLinkedin")
    public String updateLinkedin(@RequestParam String linkedinlink,@RequestParam int cvId){
        Result result=this.cvService.updateLinkedin(linkedinlink,cvId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @DeleteMapping("/deleteLinkedin")
    public String deleteLinkedin(@RequestParam int cvId){
        Result result=this.cvService.deleteLinkedin(cvId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @PutMapping("/updateBiography")
    public String updateBiography(@RequestParam String biography,@RequestParam int cvId){
        Result result=this.cvService.updateBiography(biography,cvId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @DeleteMapping("/deleteBiography")
    public String deleteBiography(@RequestParam int cvId){
        Result result=this.cvService.deleteBiography(cvId);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result) );
    }
}