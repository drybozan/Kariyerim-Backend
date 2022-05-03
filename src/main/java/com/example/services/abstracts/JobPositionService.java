package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.JobPosition;

import java.util.List;



public interface JobPositionService {
	DataResult<List<JobPosition>> getAll();
    Result add(JobPosition jobPosition);
    DataResult<JobPosition> getByName(String name);
}
