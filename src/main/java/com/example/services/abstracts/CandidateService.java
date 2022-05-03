package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Candidate;
import com.example.entities.dtos.CandidateForRegisterDto;

import java.util.List;



public interface CandidateService{
		DataResult<List<Candidate>> getAll();
	    DataResult<Candidate> getByNationalNumber(String nationalNumber);
	    DataResult<Candidate> getByEmail(String email);
	    Result add(CandidateForRegisterDto candidateForRegisterDto);
	  

}
