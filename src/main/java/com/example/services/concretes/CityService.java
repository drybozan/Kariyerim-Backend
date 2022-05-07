package com.example.services.concretes;

import com.example.dataAcces.CityDao;
import com.example.entities.concretes.City;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;





@Service
public class CityService  {

    private CityDao cityDao;

    @Autowired
    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }


    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>((List<City>) this.cityDao.getAll(),"Data listelendi");
    }
}
