package com.example.services.concretes;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.SuccessDataResult;
import com.example.dataAcces.WorkTimeDao;
import com.example.entities.concretes.WorkTime;
import com.example.services.abstracts.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class WorkTimeManager implements WorkTimeService {

    private WorkTimeDao workTimeDao;

    @Autowired
    public WorkTimeManager(WorkTimeDao workTimeDao) {
        this.workTimeDao = workTimeDao;
    }

    //@Override
    public DataResult<List<WorkTime>> getAll() {
        return new SuccessDataResult<List<WorkTime>>(this.workTimeDao.findAll(),"Data listelendi");
    }
}
