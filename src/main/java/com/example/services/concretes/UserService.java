package com.example.services.concretes;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.ErrorDataResult;
import com.example.utilities.results.SuccessDataResult;
import com.example.dataAcces.CandidateDao;
import com.example.dataAcces.EmployerDao;
import com.example.dataAcces.UserDao;
import com.example.entities.concretes.User;
import com.example.entities.dtos.UserLoginDto;
import com.example.entities.dtos.UserLoginReturnDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class  UserService {

    private UserDao userDao;
    private CandidateDao candidateDao;
    private EmployerDao employerDao;


    @Autowired
    public  UserService(UserDao userDao,CandidateDao candidateDao,EmployerDao employerDao) {
        this.userDao = userDao;
        this.candidateDao=candidateDao;
        this.employerDao=employerDao;
       
    }

    //@Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>((List<User>) this.userDao.getAll(),"Data listelendi");
    }

    //@Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.findByEmail(email),"Listelendi");
    }

    //@Override
    public DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto) {
        User user = this.userDao.findByEmail(userLoginDto.getEmail());
        if(user==null){
            return new ErrorDataResult<UserLoginReturnDto>("Hatalı email girdiniz");
        }else if(!user.getPassword().equals(userLoginDto.getPassword())){
            return new ErrorDataResult<UserLoginReturnDto>("Hatalı şifre girdiniz");
        }
        UserLoginReturnDto userLoginReturnDto = new UserLoginReturnDto();
        userLoginReturnDto.setId(user.getId());
        userLoginReturnDto.setEmail(user.getEmail());

        if(this.employerDao.getById(user.getId()) != null){
            userLoginReturnDto.setUserType(2);
            userLoginReturnDto.setName(this.employerDao.getById(user.getId()).getCompanyName());
       
        }else {
            return new ErrorDataResult<UserLoginReturnDto>("Bir hata oluştu");
        }

        return new SuccessDataResult<UserLoginReturnDto>(userLoginReturnDto,"Giriş yapıldı");
    }

   
}

