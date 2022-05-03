package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.entities.concretes.WorkPlace;

import java.util.List;



public interface WorkPlaceService {
	 public DataResult<List<WorkPlace>> getAll();
}
