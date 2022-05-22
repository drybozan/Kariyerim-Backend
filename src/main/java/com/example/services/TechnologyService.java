package com.example.services;

import com.example.dataAcces.CvDao;
import com.example.dataAcces.TechnologyDao;
import com.example.entities.concretes.Technology;
import com.example.entities.dtos.TechnologyForSerDto;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
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
        technology.setCv_id(this.cvDao.getByCvId(technologyForSerDto.getCvId()).getId());
        technology.setName(technologyForSerDto.getName());

        this.technologyDao.save(technology);
        return new SuccessResult("Eklendi");
    }

    public Result deleteTechnology(int technologyId) {
        Technology tec = this.technologyDao.getById(technologyId);
        if(tec==null){
            return new ErrorResult("Böyle bir teknoloji yok");
        }
        this.technologyDao.deleteById(tec);
        return new SuccessResult("Silindi");
    }

    public DataResult<List<Technology>> getByCvId(int cvId) {
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorDataResult<List<Technology>>("Böyle bir cv yok");
        }

        return new SuccessDataResult<List<Technology>>((List<Technology>) this.technologyDao.findByCvId(cvId),"Data listelendi");
    }
}
