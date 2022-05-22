package com.example.services;

import com.example.dataAcces.CityDao;
import com.example.entities.concretes.City;
import com.example.utilities.results.DataResult;
import com.example.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;





@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CityService  {

    @Autowired
    private CityDao cityDao;




    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>((List<City>) this.cityDao.getAll(),"Data listelendi");
    }
}
