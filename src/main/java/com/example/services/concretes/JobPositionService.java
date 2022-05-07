package com.example.services.concretes;

import com.example.utilities.results.*;
import com.example.dataAcces.JobPositionDao;
import com.example.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class  JobPositionService {

    private JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionService(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    //@Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>((List<JobPosition>) this.jobPositionDao.getAll(),"Data listelendi");
    }

   // @Override
    public Result add(JobPosition jobPosition) {
        if(getByName(jobPosition.getName()).getData() != null){
            return new ErrorResult("Bu isimde bir pozisyon zaten kayıtlı");
        }else if(jobPosition.getName().length() <=2){
            return new ErrorResult("İş ismi 2 karakterden kısa olamaz");
        }else{
            this.jobPositionDao.save(jobPosition);
            return new SuccessResult("İş eklendi");
        }
    }

   // @Override
    public DataResult<JobPosition> getByName(String name) {
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.findByName(name),"Listelendi");
    }
}
