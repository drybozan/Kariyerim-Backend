package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.entities.concretes.User;
import com.example.entities.dtos.UserLoginDto;
import com.example.entities.dtos.UserLoginReturnDto;

import java.util.List;



public interface UserService {
	  	DataResult<List<User>> getAll();
	    DataResult<User> getByEmail(String email);
	    DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto);
	    

}
