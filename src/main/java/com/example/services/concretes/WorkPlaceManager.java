package com.example.services.concretes;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.SuccessDataResult;
import com.example.dataAcces.WorkPlaceDao;
import com.example.entities.concretes.WorkPlace;
import com.example.services.abstracts.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class WorkPlaceManager implements WorkPlaceService {

    private WorkPlaceDao workPlaceDao;

    @Autowired
    public WorkPlaceManager(WorkPlaceDao workPlaceDao) {
        this.workPlaceDao = workPlaceDao;
    }

    //@Override
    public DataResult<List<WorkPlace>> getAll() {
        return new SuccessDataResult<List<WorkPlace>>(this.workPlaceDao.findAll(),"Data listelendi");
    }
  }
