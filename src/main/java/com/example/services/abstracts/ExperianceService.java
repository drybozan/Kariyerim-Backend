package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Experiance;
import com.example.entities.dtos.ExperianceForSetDto;

import java.util.List;



public interface ExperianceService {
	Result add(ExperianceForSetDto experianceForSetDto);
    Result delete(int experianceId);
    DataResult<List<Experiance>> getByCvId(int id);
}
