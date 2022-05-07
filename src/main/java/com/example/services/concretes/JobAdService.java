package com.example.services.concretes;

import com.example.dataAcces.*;
import com.example.entities.concretes.JobAd;
import com.example.entities.dtos.JobAdDto;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class JobAdService {

    private JobAdDao jobAdDao;
    private JobPositionDao jobPositionDao;
    private EmployerDao employerDao;
    private CityDao cityDao;
    private WorkPlaceDao workPlaceDao;
    private WorkTimeDao workTimeDao;

    @Autowired
    public JobAdService(JobAdDao jobAdDao,JobPositionDao jobPositionDao,EmployerDao employerDao,CityDao cityDao,WorkPlaceDao workPlaceDao,WorkTimeDao workTimeDao) {
        this.jobAdDao = jobAdDao;
        this.jobPositionDao=jobPositionDao;
        this.employerDao=employerDao;
        this.cityDao=cityDao;
        this.workPlaceDao=workPlaceDao;
        this.workTimeDao=workTimeDao;
    }

    public Result create(JobAdDto jobAdDto) {

        if(cityDao.getById(jobAdDto.getCityId())==null){
            return new ErrorResult("Şehir bulunamadı");
        }
        else if(this.employerDao.getById(jobAdDto.getEmployerId())==null){
            return new ErrorResult("İş veren bulunamadı");
        }
        else if(jobAdDto.getDescription().isEmpty()){
            return new ErrorResult("Açıklama boş birakılamaz");
        }
        else if(jobAdDto.getMinSalary()==0){
            return new ErrorResult("Minumum maaş 0 verilemez");
        }
        else if(jobAdDto.getMaxSalary()==0){
            return new ErrorResult("Maximum maaş sıfır verilemez");
        }
        else if(jobAdDto.getMinSalary() >= jobAdDto.getMaxSalary()){
            return new ErrorResult("Minumum maaş maksimum maala eşit yada büyük olamaz");
        }
        else if(jobAdDto.getOpenPositions()<1){
            return new ErrorResult("Açık pozisyon adeti 1 den küçük olamaz");
        }
        else if(jobAdDto.getLastDate() == null){
            return new ErrorResult("Son başvuru tarihi boş bırakılamaz");
        }else if(this.workPlaceDao.getById(jobAdDto.getWorkPlaceId())==null){
            return new ErrorResult("Geçersiz Çalışma yeri");
        }else if(this.workTimeDao.getById(jobAdDto.getWorkTimeId())==null){
            return new ErrorResult("Geçersiz çalışma zamanı");
        }

        JobAd jobAd=new JobAd();
        jobAd.setId(0);
        jobAd.setJobPosition(this.jobPositionDao.getById(jobAdDto.getJobPositionId()));
        jobAd.setEmployer(this.employerDao.getById(jobAdDto.getEmployerId()));
        jobAd.setDescription(jobAdDto.getDescription());
        jobAd.setCity(this.cityDao.getById(jobAdDto.getCityId()));
        jobAd.setMinSalary(jobAdDto.getMinSalary());
        jobAd.setMaxSalary(jobAdDto.getMaxSalary());
        jobAd.setOpenPositions(jobAdDto.getOpenPositions());
        jobAd.setLastDate(jobAdDto.getLastDate());
        jobAd.setCreateDate(LocalDate.now());
        jobAd.setWorkPlace(this.workPlaceDao.getById(jobAdDto.getWorkPlaceId()));
        jobAd.setWorkTime(this.workTimeDao.getById(jobAdDto.getWorkTimeId()));
        this.jobAdDao.save(jobAd);

        return new SuccessResult("İlan başarılı bir şekilde eklendi");
    }

    public Result setPasssive(int jobAdId) {
        try {
            JobAd jobAd=this.jobAdDao.getById(jobAdId);
           
            jobAdDao.save(jobAd);
            return new SuccessResult("İş ilanı pasifleştirildi");
        }catch (EntityNotFoundException exception){
            return new ErrorResult("İş ilanı bulunamadı");
        }
    }

    public DataResult<List<JobAd>> getAll() {
        return new SuccessDataResult<List<JobAd>>((List<JobAd>) this.jobAdDao.getAll(),"Data listelendi");
    }

    public DataResult<JobAd> getByJobAdId(int id) {
        if(this.jobAdDao.getById(id)==null){
            return new ErrorDataResult<JobAd>("Böyle bir ilan yok");
        }
        return new SuccessDataResult<JobAd>(this.jobAdDao.getById(id),"Data listelendi");
    }

//    public DataResult<List<JobAd>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize, JobAdFilter jobAdFilter) {
//        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
//        return new SuccessDataResult<List<JobAd>>(this.jobAdDao.getByFilter(jobAdFilter, pageable).getContent(), this.jobAdDao.getByFilter(jobAdFilter,pageable).getTotalElements()+"");
//    }
}

