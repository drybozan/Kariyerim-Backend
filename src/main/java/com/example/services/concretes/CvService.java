package com.example.services.concretes;

import com.example.dataAcces.CandidateDao;
import com.example.dataAcces.CvDao;
import com.example.entities.concretes.Cv;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
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
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorDataResult<Cv>("Böyle bir cv yok");
        }
        return new SuccessDataResult<Cv>(this.cvDao.getByCvId(cvId),"Data listelendi");
    }


    public DataResult<Cv> getByCandidateId(int candidateId) {
        if(this.cvDao.findByCandidateId(candidateId) == null){
            return new ErrorDataResult<Cv>("Böyle bir candidate yok");
        }
        return new SuccessDataResult<Cv>(this.cvDao.findByCandidateId(candidateId),"Data listelendi");
    }


    public Result updateGithub(String githublink, int cvId) {
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorResult("Böyle bir cv yok");
        }else if(!githublink.startsWith("https://github.com")){
            if(!githublink.startsWith("github.com")){
                return new ErrorResult("Geçerli bir github linki değil");
            }
        }
        Cv cv=this.cvDao.getByCvId(cvId);
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
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorResult("Böyle bir cv yok");
        }else if(!linkedinlink.startsWith("https://www.linkedin.com") &&
                !linkedinlink.startsWith("www.linkedin.com") &&
                !linkedinlink.startsWith("https://linkedin.com") &&
                !linkedinlink.startsWith("linkedin.com")){
            return new ErrorResult("Geçerli bir linked in adresi değil");
        }
        Cv cv=this.cvDao.getByCvId(cvId);
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

