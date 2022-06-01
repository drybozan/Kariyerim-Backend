package com.example.controllers;

import com.example.Util;
import com.example.entities.concretes.JobAd;
import com.example.entities.dtos.JobAdDto;
import com.example.services.JobAdService;
import com.example.utilities.results.ErrorDataResult;
import com.example.utilities.results.Result;
import com.example.utilities.results.SuccessDataResult;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/jobAd")
@CrossOrigin
public class JobAdController {

    private JobAdService jobAdService;

    @Autowired
    public JobAdController(JobAdService jobAdService) {
        this.jobAdService = jobAdService;
    }

    @GetMapping("/getall")
    public String getAll() {
        return Util.ConvertToJsonString(this.jobAdService.getAll());
    }


    @GetMapping("/getByJobAdId")
    public String getByJobAdId(@RequestParam int id) {
        JobAd jobAdForSet = this.jobAdService.getByJobAdId(id).getData();
        if (jobAdForSet == null) {
            return Util.ConvertToJsonString(Util.ConvertToJsonString(
                    new ErrorDataResult<JobAd>("Böyle bir ilan yok")));
        }
        return Util.ConvertToJsonString(new SuccessDataResult<JobAd>(jobAdForSet, "Data listelendi"));
    }

    @PostMapping("/create")
    public String create(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
        JSONObject requestBody = JSONObject.fromObject(json);
        JobAdDto nesne = new JobAdDto();
        nesne.setCityId(requestBody.getInt("cityId"));
        nesne.setJobPositionId(requestBody.getInt("jobPositionId"));
        nesne.setEmployerId(requestBody.getInt("employerId"));
        nesne.setWorkPlaceId(requestBody.getInt("workPlaceId"));
        nesne.setWorkTimeId(requestBody.getInt("workTimeId"));
        nesne.setDescription(requestBody.getString("description"));
        nesne.setOpenPositions(requestBody.getInt("openPositions"));
        nesne.setLastDate(Util.ConvertToDate(requestBody.getString("lastDate")));
        nesne.setMinSalary(requestBody.getInt("minSalary"));
        nesne.setMaxSalary(requestBody.getInt("maxSalary"));
        Result result = this.jobAdService.create(nesne);
        if (result.isSuccess()) {
            return Util.ConvertToJsonString(ResponseEntity.ok(result));
        }
        return Util.ConvertToJsonString(ResponseEntity.badRequest().body(result));
    }

    @PostMapping("/getByActiveAndFilter")
    public String getByActiveAndFilter(@RequestParam int pageNo, @RequestParam int pageSize,
                                       @RequestBody String jobAdFilter) {
            if (jobAdFilter.equals("{}"))
                return Util.ConvertToJsonString(this.jobAdService.getAll(pageNo, pageSize));
            JSONObject requestBody = JSONObject.fromObject(jobAdFilter);
            Integer[] cityId = null, jobPositionId = null, workPlaceId = null, workTimeId = null;
            try {cityId = Util.JsonArrayToIntArray(requestBody.getJSONArray("cityId"));}
            catch (JSONException e) { }
            try { jobPositionId = Util.JsonArrayToIntArray(requestBody.getJSONArray("jobPositionId")); }
            catch (JSONException e) { }
            try { workPlaceId = Util.JsonArrayToIntArray(requestBody.getJSONArray("workPlaceId"));}
            catch(JSONException e){ }
            try { workTimeId = Util.JsonArrayToIntArray(requestBody.getJSONArray("workTimeId")); }
            catch (JSONException e) { }

            return Util.ConvertToJsonString(jobAdService.getByFilter(pageNo, pageSize,
                    cityId, jobPositionId, workPlaceId, workTimeId));
        }
    }

