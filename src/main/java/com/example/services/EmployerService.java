package com.example.services;

import com.example.dataAcces.EmployerDao;
import com.example.entities.concretes.Employer;
import com.example.entities.dtos.EmployerForRegisterDto;
import com.example.utilities.results.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;




@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class EmployerService  {


    private EmployerDao employerDao;
    private UserService userService;


    //@Autowired
    public EmployerService(EmployerDao employerDao,UserService userService) {
        this.employerDao = employerDao;
        this.userService=userService;
       
    }

    //@Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>((List<Employer>) this.employerDao.getAll(),"Data listelendi");
    }

    //@Override
    public DataResult<Employer> getByEmail(String email) {
        return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email),"Listelendi");
    }

    //@Override
    public Result add(EmployerForRegisterDto employerDto) {
        if(!employerDto.getPassword().equals(employerDto.getRePassword())){
            return new ErrorResult("Şifreler eşleşmiyor");
        }
        Employer employer=new Employer();
        employer.setEmail(employerDto.getEmail());
        employer.setPassword(employerDto.getPassword());
        employer.setCompanyName(employerDto.getCompanyName());
        employer.setWebSite(employerDto.getWebSite());
        employer.setPhoneNumber(employerDto.getPhoneNumber());
        


       if(!isEmailValid(employer.getEmail())){
           return new ErrorResult("Geçerli bir email giriniz");
       }else if(!employer.getEmail().endsWith(employer.getWebSite())){
           return new ErrorResult("Web siteniz ve emailinizin domaini aynı olmalıdır");
       }else if(employer.getPassword().length() < 4 ){
           return new ErrorResult("Şifre 4 karakterden uzun olmalıdır.");
       }else if(employer.getPhoneNumber().length() <10){
           return new ErrorResult("Geçerli bir telefon numarası giriniz.");
       }else if(employer.getCompanyName().length()<=2){
           return new ErrorResult("Şirket adı 2 karakterden uzun olmalıdır");
       }
       this.employerDao.save(employer);
       	return new SuccessResult(" Kaydedildiniz");

    }

    //@Override
    public DataResult<Employer> getById(int id) {
        if(this.employerDao.getById(id)==null){
            return new ErrorDataResult<Employer>("Böyle bir işveren yok");
        }
        return new SuccessDataResult<Employer>(this.employerDao.getById(id),"Data listelendi");
    }

    private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }


}

