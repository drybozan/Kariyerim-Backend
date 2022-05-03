package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.JobAd;
import com.example.entities.dtos.JobAdDto;
import com.example.entities.dtos.JobAdFilter;

import java.util.List;


public interface JobAdService {
	 	Result create(JobAdDto jobAdDto);
	    DataResult<List<JobAd>> getAll();
	    DataResult<JobAd> getByJobAdId(int id);
	    DataResult<List<JobAd>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize, JobAdFilter jobAdFilter);
	}
