package com.example.services;

import com.example.dataAcces.CandidateDao;
import com.example.dataAcces.JobAdDao;
import com.example.dataAcces.JobAdFavoritesDao;
import com.example.entities.concretes.JobAdFavorites;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
@Service
public class JobAdFavoritesService {

    private JobAdFavoritesDao jobAdFavoritesDao;
    private CandidateDao candidateDao;
    private JobAdDao jobAdDao;

    @Autowired
    public JobAdFavoritesService(JobAdFavoritesDao jobAdFavoritesDao,CandidateDao candidateDao, JobAdDao jobAdDao) {
        this.jobAdFavoritesDao = jobAdFavoritesDao;
        this.candidateDao=candidateDao;
        this.jobAdDao=jobAdDao;
    }

    public DataResult<List<JobAdFavorites>> getByCandidateId(int candidateId) {
        return new SuccessDataResult<List<JobAdFavorites>>((List<JobAdFavorites>) this.jobAdFavoritesDao.findByCandidateId(candidateId),"Data listelendi");
    }

    public Result addFavorite(int candidateId, int jobAdId) {
        if(this.jobAdDao.getById(jobAdId)==null){
            return new ErrorResult("Böyle bir ilan yok");
        }else if(this.jobAdFavoritesDao.existsByCandidate_IdAndJobAd_Id(candidateId,jobAdId)){
            return new ErrorResult("Bu ilan zaten favorilerinizde");
        }

        JobAdFavorites jobAdFavorites=new JobAdFavorites();
        jobAdFavorites.setCandidate(this.candidateDao.getById(candidateId));
        jobAdFavorites.setJobAd(this.jobAdDao.getById(jobAdId));
        this.jobAdFavoritesDao.save(jobAdFavorites);
        return new SuccessResult("İlan favorilere eklendi");
    }

    public Result removeFavorite(int favoriteId) {
        if(this.jobAdFavoritesDao.getById(favoriteId)==null){
            return new ErrorResult("Böyle bir favori ilan yok");
        }
        this.jobAdFavoritesDao.deleteById(favoriteId);
        return new SuccessResult("İlan favorilerden kandırıldı");
    }
}
