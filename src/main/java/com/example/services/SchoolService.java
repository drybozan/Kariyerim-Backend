package com.example.services;

import com.example.dataAcces.CvDao;
import com.example.dataAcces.SchoolDao;
import com.example.entities.concretes.School;
import com.example.entities.dtos.SchoolForSerDto;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
@Service
public class SchoolService {

    private SchoolDao schoolDao;
    private CvDao cvDao;

    @Autowired
    public SchoolService(SchoolDao schoolDao,CvDao cvDao) {
        this.schoolDao = schoolDao;
        this.cvDao=cvDao;
    }

    public Result addSchool(SchoolForSerDto schoolForSerDto) {

        if(this.cvDao.getByCvId(schoolForSerDto.getCvId())==null){
            return new ErrorResult("Böyle bir cv yok");
        }else if(schoolForSerDto.getName().length()<=2){
            return new ErrorResult("Okul adı 2 karakterden uzun olmalıdır");
        }else if(schoolForSerDto.getDepartment().length()<=2){
            return new ErrorResult("Bölüm adı 2 karakterden uzun olmalıdır");
        }else if(schoolForSerDto.getStartDate()==null){
            return new ErrorResult("Başlangıç tarihi boş birakılamaz");
        }

        School school=new School();
        school.setCv_id(this.cvDao.getByCvId(schoolForSerDto.getCvId()).getId());
        school.setName(schoolForSerDto.getName());
        school.setDepartment(schoolForSerDto.getDepartment());
        school.setStartDate(schoolForSerDto.getStartDate());
        school.setEndDate(schoolForSerDto.getEndDate());

        this.schoolDao.save(school);
        return new SuccessResult("Okul eklendi");
    }

    public Result deleteSchool(int schoolId) {
        School school = this.schoolDao.getById(schoolId);
        if(school==null){
            return new ErrorResult("Böyle bir okul yok");
        }
        this.schoolDao.deleteById(school);
        return new SuccessResult("Okul silindi");
    }

    public DataResult<List<School>> getByCvId(int cvId) {
        if(this.schoolDao.findByCvId(cvId)==null){
            return new ErrorDataResult<List<School>>("Böyle bir cv yok");
        }
        return new SuccessDataResult<List<School>>((List<School>) this.schoolDao.findByCvId(cvId),"Data listelendi");
    }
}
