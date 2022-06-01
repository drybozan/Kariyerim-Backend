package com.example.services;

import com.example.dataAcces.CvDao;
import com.example.dataAcces.LanguageDao;
import com.example.entities.concretes.Language;
import com.example.entities.dtos.LanguageForSetDto;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
@Service
public class  LanguageService {

    private LanguageDao languageDao;
    private CvDao cvDao;

    @Autowired
    public  LanguageService(LanguageDao languageDao,CvDao cvDao) {
        this.languageDao = languageDao;
        this.cvDao=cvDao;
    }

    public Result addLanguage(LanguageForSetDto languageForSetDto) {
        if(this.cvDao.getByCvId(languageForSetDto.getCvId())==null){
            return new ErrorResult("Böyle bir cv yok");
        }else if(languageForSetDto.getName().length()<=2){
            return new ErrorResult("Dil ismi 2 karakterden uzun olmalıdır");
        }else if(Integer.parseInt(languageForSetDto.getLevel()) <=0 || Integer.parseInt(languageForSetDto.getLevel()) >=6){
            return new ErrorResult("Dil seviyesi 1-5 arası bir değer olmalıdır");
        }
        Language language=new Language();
        //language.setCv(this.cvDao.getByCvId(languageForSetDto.getCvId()));
        language.setName(languageForSetDto.getName());
        language.setLevel(languageForSetDto.getLevel());
        language.setCv_id(languageForSetDto.getCvId());
        this.languageDao.save(language);
        return new SuccessResult("Dil kaydedildi");
    }

    public Result deleteLanguage(int languageId) {
        Language lan = this.languageDao.getById(languageId);
        if(lan == null){
            return new ErrorResult("Böyle bir dil bulunamadı");
        }
        this.languageDao.deleteById(lan);
        return new SuccessResult("Silindi");
    }

    public DataResult<List<Language>> getByCvId(int cvId) {
        if(this.cvDao.getByCvId(cvId)==null){
            return new ErrorDataResult<List<Language>>("Böyle bir cv yok");
        }
        return new SuccessDataResult<List<Language>>((List<Language>) this.languageDao.findByCvId(cvId),"Listelendi");
    }
}
