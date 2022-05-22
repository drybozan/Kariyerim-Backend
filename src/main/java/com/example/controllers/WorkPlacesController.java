package com.example.controllers;

import com.example.Util;
import com.example.services.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/workPlace")
@CrossOrigin
public class WorkPlacesController {

    private WorkPlaceService workPlaceService;

    private static Logger logger = LoggerFactory.getLogger(WorkPlacesController.class);

    @Autowired
    public WorkPlacesController(WorkPlaceService workPlaceService) {
        this.workPlaceService = workPlaceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<WorkPlace>> getAll(){
        logger.info("WorkPlacesController class'ı getAll() metodu çalıştı");
        return this.workPlaceService.getAll();
    }
}
