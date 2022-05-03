package com.example.services.abstracts;


import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Employer;
import com.example.entities.dtos.EmployerForRegisterDto;

import java.util.List;


public interface EmployerService {
	DataResult<List<Employer>> getAll();
    DataResult<Employer> getByEmail(String email);
    Result add(EmployerForRegisterDto employerDto);
    DataResult<Employer> getById(int id);
    
}
