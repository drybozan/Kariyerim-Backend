package com.example.controllers;

import com.example.Util;
import com.example.entities.concretes.JobAd;
import com.example.entities.dtos.JobAdDto;
import com.example.services.JobAdService;
import com.example.utilities.results.ErrorDataResult;
import com.example.utilities.results.Result;
import com.example.utilities.results.SuccessDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobAd")
@CrossOrigin
public class JobAdController {

    private JobAdService jobAdService;

    private static Logger logger = LoggerFactory.getLogger(JobAdController.class);

    @Autowired
    public JobAdController(JobAdService jobAdService) {
        this.jobAdService = jobAdService;
    }

    @GetMapping("/getall")
    public String getAll(){
        logger.info("JobAdController Class'ı getAll() metodu çalıştı");
        return Util.ConvertToJsonString(this.jobAdService.getAll());
    }

    @GetMapping("/getByJobAdId")
    public String getByJobAdId(@RequestParam int id){
        logger.info("JobAdController Class'ı getByJobAdId() metodu çalıştı");
        JobAd jobAd=new JobAd();
        JobAd jobAdForSet=this.jobAdService.getByJobAdId(id).getData();
        if(jobAdForSet==null){
            return Util.ConvertToJsonString(Util.ConvertToJsonString(new ErrorDataResult<JobAd>("Böyle bir ilan yok")));
        }
        jobAd.setId(jobAdForSet.getId());
        jobAd.setEmployer(jobAdForSet.getEmployer());
        jobAd.setJobPosition(jobAdForSet.getJobPosition());
        jobAd.setDescription(jobAdForSet.getDescription());
        jobAd.setCity(jobAdForSet.getCity());
        jobAd.setMinSalary(jobAdForSet.getMinSalary());
        jobAd.setMaxSalary(jobAdForSet.getMaxSalary());
        jobAd.setOpenPositions(jobAdForSet.getOpenPositions());
        jobAd.setLastDate(jobAdForSet.getLastDate());
        jobAd.setCreateDate(jobAdForSet.getCreateDate());
        jobAd.setWorkPlace(jobAdForSet.getWorkPlace());
        jobAd.setWorkTime(jobAdForSet.getWorkTime());

        return Util.ConvertToJsonString(new SuccessDataResult<JobAd>(jobAd,"Data listelendi"));
    	
    }

    @PostMapping("/create")
    public String create(@RequestBody JobAdDto jobAdDto){
        logger.info("JobAdController Class'ı create() metodu çalıştı");
        Result result=this.jobAdService.create(jobAdDto);
        if(result.isSuccess()){
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

//    @PostMapping("/getByActiveAndFilter")
//    public Result getByActiveAndFilter(@RequestParam int pageNo,@RequestParam int pageSize,@RequestBody JobAdFilter jobAdFilter){
//        return jobAdService.getByIsActiveAndPageNumberAndFilter(pageNo,pageSize,jobAdFilter);
//    }
}

