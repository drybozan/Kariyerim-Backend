package com.example.services;

import com.example.dataAcces.CvDao;
import com.example.dataAcces.ExperianceDao;
import com.example.entities.concretes.Experiance;
import com.example.entities.dtos.ExperianceForSetDto;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
@Service
public class  ExperianceService {

    private ExperianceDao experianceDto;
    private CvDao cvDao;

    @Autowired
    public ExperianceService(ExperianceDao experianceDto, CvDao cvDao) {
        this.experianceDto = experianceDto;
        this.cvDao=cvDao;
    }


    public Result add(ExperianceForSetDto experianceForSetDto) {

        if(this.cvDao.getByCvId(experianceForSetDto.getCvId())==null){
            return new ErrorResult("Böyle bir cv yok");
        }else if(experianceForSetDto.getCompanyName().length()<=2){
            return new ErrorResult("Şirket adı 2 karakterden uzun olmalıdır");
        }else if(experianceForSetDto.getPosition().length()<=2){
            return new ErrorResult("Pozisyon adı 2 karakterden uzun olmalıdır");
        }else if(experianceForSetDto.getStartDate() == null){
            return new ErrorResult("Başlangıç tarihi boş bırakılamaz");
        }

        Experiance experiance=new Experiance();
        experiance.setCv(this.cvDao.getByCvId((experianceForSetDto.getCvId())));
        experiance.setCompanyName(experianceForSetDto.getCompanyName());
        experiance.setPosition(experianceForSetDto.getPosition());
        experiance.setStartDate(experianceForSetDto.getStartDate());
        experiance.setEndDate(experianceForSetDto.getEndDate());

        this.experianceDto.save(experiance);
        return new SuccessResult("Kaydedildi");
    }


    public Result delete(int experianceId) {
        if(this.experianceDto.getById(experianceId)==null){
            return new ErrorResult("Böyle bir tecrübe yok");
        }
        this.experianceDto.deleteById(experianceId);
        return new SuccessResult("Silindi");
    }


    public DataResult<List<Experiance>> getByCvId(int id) {

        return new SuccessDataResult<List<Experiance>>((List<Experiance>) this.experianceDto.findByCvId(id),"Data listelendi");
    }
}

