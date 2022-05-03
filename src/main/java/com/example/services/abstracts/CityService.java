package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.entities.concretes.City;

import java.util.List;



public interface CityService {
	public DataResult<List<City>> getAll();
}
