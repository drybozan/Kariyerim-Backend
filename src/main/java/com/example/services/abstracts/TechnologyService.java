package com.example.services.abstracts;


import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Technology;
import com.example.entities.dtos.TechnologyForSerDto;

import java.util.List;

public interface TechnologyService {
		public Result addTechnology(TechnologyForSerDto technologyForSerDto);
	    public Result deleteTechnology(int technologyId);
	    public DataResult<List<Technology>> getByCvId(int cvId);
}
