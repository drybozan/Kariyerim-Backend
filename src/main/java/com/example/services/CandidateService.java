package com.example.services;


import com.example.dataAcces.CandidateDao;
import com.example.entities.concretes.Candidate;
import com.example.entities.dtos.CandidateForRegisterDto;
import com.example.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;



@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = Exception.class)
public class CandidateService {

    private CandidateDao candidateDao;
    private UserService userService;
  
    @Autowired
    public CandidateService(CandidateDao candidateDao,UserService userService) {
        this.candidateDao = candidateDao;
        this.userService=userService;
        
    }

    //@Override
    public DataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<List<Candidate>>((List<Candidate>) this.candidateDao.getAll(),"Data listelendi");
    }

    //@Override
    public DataResult<Candidate> getByNationalNumber(String nationalNumber) {
        return new SuccessDataResult<Candidate>(this.candidateDao.findByNationalNumber(nationalNumber),"Listelendi");
    }


   // @Override
    public Result add(CandidateForRegisterDto candidateDto) {
        if(!candidateDto.getPassword().equals(candidateDto.getRePassword())){//şifre tekrarı için
            return new ErrorResult("Şifreler eşleşmiyor");
        }
        Candidate candidate=new Candidate();
        candidate.setFirstName(candidateDto.getFirstName());
        candidate.setLastName(candidateDto.getLastName());
        candidate.setNationalNumber(candidateDto.getNationalNumber());
        candidate.setDateOfBirth(candidateDto.getBirthDate());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setPassword(candidateDto.getPassword());

        if(candidate.getPassword().length() <=4){
            return new ErrorResult("Şifre 4 karakterden uzun olmalıdır");
        }else if(!isEmailValid(candidate.getEmail())){
            return new ErrorResult("Email geçerli formatta değil");
        }else{
            this.candidateDao.save(candidate);
            return new SuccessResult("Kullanıcı kaydedildi");
        }


    }


    private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }
}

