package com.example.controllers;

import com.example.entities.concretes.WorkTime;
import com.example.services.concretes.WorkTimeService;
import com.example.utilities.results.DataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(WorkTimesController.class);

    @Autowired
    public WorkTimesController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }

    @GetMapping("/getAll")
    public DataResult<List<WorkTime>> getAll(){
        logger.info("WorkPlacesController class'ı getAll() metodu çalıştı");
        return this.workTimeService.getAll();
    }
}