package com.example.services;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.SuccessDataResult;
import com.example.dataAcces.WorkPlaceDao;
import com.example.entities.concretes.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
@Service
public class  WorkPlaceService {

    private WorkPlaceDao workPlaceDao;

    @Autowired
    public WorkPlaceService(WorkPlaceDao workPlaceDao) {
        this.workPlaceDao = workPlaceDao;
    }

    //@Override
    public DataResult<List<WorkPlace>> getAll() {
        return new SuccessDataResult<List<WorkPlace>>((List<WorkPlace>) this.workPlaceDao.getAll(),"Data listelendi");
    }
  }
