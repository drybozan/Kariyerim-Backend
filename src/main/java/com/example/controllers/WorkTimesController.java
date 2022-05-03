package com.example.controllers;

import com.example.utilities.results.DataResult;
import com.example.entities.concretes.WorkTime;
import com.example.services.abstracts.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;




@RestController
@RequestMapping("/workTime")
@CrossOrigin
public class WorkTimesController {

    private WorkTimeService workTimeService;

    @Autowired
    public WorkTimesController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }

    @GetMapping("/getAll")
    public DataResult<List<WorkTime>> getAll(){
        return this.workTimeService.getAll();
    }
}