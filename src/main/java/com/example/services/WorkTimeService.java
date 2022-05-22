package com.example.services;

import com.example.dataAcces.WorkTimeDao;
import com.example.entities.concretes.WorkTime;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
@Service
public class WorkTimeService {

    private WorkTimeDao workTimeDao;

    @Autowired
    public WorkTimeService(WorkTimeDao workTimeDao) {
        this.workTimeDao = workTimeDao;
    }

    //@Override
    public DataResult<List<WorkTime>> getAll() {
        return new SuccessDataResult<List<WorkTime>>((List<WorkTime>) this.workTimeDao.getAll(),"Data listelendi");
    }
}
