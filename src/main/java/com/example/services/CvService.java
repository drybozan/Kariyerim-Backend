package com.example.services;

import com.example.dataAcces.CandidateDao;
import com.example.dataAcces.CvDao;
import com.example.entities.concretes.Cv;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CvService  {

    private CvDao cvDao;
    private CandidateDao candidateDao;
    

    @Autowired
    public CvService(CvDao cvDao,CandidateDao candidateDao) {
        this.cvDao = cvDao;
        this.candidateDao=candidateDao;
       
    }

    public Result add(int candidateId) {
        Cv cv=new Cv();
        cv.setCandidate(this.candidateDao.getById(candidateId));

        this.cvDao.save(cv);
        return new SuccessResult("Kaydedildi");
    }

    public DataResult<List<Cv>> getAll() {
        return new SuccessDataResult<List<Cv>>((List<Cv>) this.cvDao.getAll(),"Data listelendi");
    }


    public DataResult<Cv> getByCvId(int cvId) {
        Cv cv = this.cvDao.getByCvId2(cvId);
        if(cv ==null){
            return new ErrorDataResult<Cv>("Böyle bir cv yok");
        }
        return new SuccessDataResult<Cv>(cv,"Data listelendi");
    }


    public DataResult<Cv> getByCandidateId(int candidateId) {
        Cv c = this.cvDao.findByCandidateId(candidateId);
        if( c== null){
            return new ErrorDataResult<Cv>("Böyle bir candidate yok");
        }
        return new SuccessDataResult<Cv>(c,"Data listelendi");
    }


    public Result updateGithub(String githublink, int cvId) {
        Cv cv = this.cvDao.getByCvId(cvId);
        if(cv==null){
            return new ErrorResult("Böyle bir cv yok");
        }
        cv.setGithub(githublink);
        this.cvDao.save(cv);
        return new SuccessResult("Kaydedildi");
    }


    public Result deleteGithub(int cvId) {
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getByCvId(cvId);
        cv.setGithub(null);
        this.cvDao.save(cv);
        return new SuccessResult("Github adresi kaldırıldı");
    }


    public Result updateLinkedin(String linkedinlink, int cvId) {
        Cv cv = this.cvDao.getByCvId(cvId);
        if(cv==null){
            return new ErrorResult("Böyle bir cv yok");
        }
        cv.setLinkedin(linkedinlink);
        this.cvDao.save(cv);
        return new SuccessResult("Kaydedildi");
    }


    public Result deleteLinkedin(int cvId) {
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getByCvId(cvId);
        cv.setLinkedin(null);
        this.cvDao.save(cv);
        return new SuccessResult("Linkedin adresi silindi");
    }


    public Result updateBiography(String biography, int cvId) {
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorResult("Böyle bir cv yok");
        }else if(biography.length()<=2){
            return new ErrorResult("Biyografi 2 krakterden uzun olmalıdır");
        }
        Cv cv=this.cvDao.getByCvId(cvId);
        cv.setBiography(biography);
        this.cvDao.save(cv);
        return new SuccessResult("Biyografi kaydedildi");
    }


    public Result deleteBiography(int cvId) {
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getByCvId(cvId);
        cv.setBiography(null);
        this.cvDao.save(cv);
        return new SuccessResult("Biyografi silindi");
    }

}

