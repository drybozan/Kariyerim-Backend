package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.School;
import com.example.entities.dtos.SchoolForSerDto;

import java.util.List;




public interface SchoolService {
	    public Result addSchool(SchoolForSerDto schoolForSerDto);
	    public Result deleteSchool(int schoolId);
	    public DataResult<List<School>> getByCvId(int cvId);

}
