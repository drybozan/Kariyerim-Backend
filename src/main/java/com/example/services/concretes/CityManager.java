package com.example.services.concretes;

import com.example.dataAcces.CityDao;
import com.example.entities.concretes.City;
import com.example.services.abstracts.CityService;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;





@Service
public class CityManager implements CityService {

    private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    //@Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll(),"Data listelendi");
    }
}
