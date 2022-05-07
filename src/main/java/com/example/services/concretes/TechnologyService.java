package com.example.services.concretes;

import com.example.dataAcces.CvDao;
import com.example.dataAcces.TechnologyDao;
import com.example.entities.concretes.Technology;
import com.example.entities.dtos.TechnologyForSerDto;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;





@Service
public class  TechnologyService {

    private TechnologyDao technologyDao;
    private CvDao cvDao;

    @Autowired
    public TechnologyService(TechnologyDao technologyDao, CvDao cvDao) {
        this.technologyDao = technologyDao;
        this.cvDao = cvDao;
    }

    public Result addTechnology(TechnologyForSerDto technologyForSerDto) {

        if(this.cvDao.getByCvId(technologyForSerDto.getCvId())==null){
            return new ErrorResult("Böyle bir cv yok");
        }else if(technologyForSerDto.getName().length()<=2){
            return new ErrorResult("Teknoloji adı 2 karekterden kısa olamaz");
        }

        Technology technology=new Technology();
        technology.setCv(this.cvDao.getByCvId(technologyForSerDto.getCvId()));
        technology.setName(technologyForSerDto.getName());

        this.technologyDao.save(technology);
        return new SuccessResult("Eklendi");
    }

    public Result deleteTechnology(int technologyId) {
        if(this.technologyDao.getById(technologyId)==null){
            return new ErrorResult("Böyle bir teknoloji yok");
        }
        this.technologyDao.deleteById(technologyId);
        return new SuccessResult("Silindi");
    }

    public DataResult<List<Technology>> getByCvId(int cvId) {
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorDataResult<List<Technology>>("Böyle bir cv yok");
        }

        return new SuccessDataResult<List<Technology>>((List<Technology>) this.technologyDao.findByCvId(cvId),"Data listelendi");
    }
}
