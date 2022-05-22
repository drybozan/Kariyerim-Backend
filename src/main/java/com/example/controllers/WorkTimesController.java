package com.example.controllers;

import com.example.Util;
import com.example.services.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




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