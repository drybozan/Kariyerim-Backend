package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.entities.concretes.WorkTime;

import java.util.List;



public interface WorkTimeService {
    public DataResult<List<WorkTime>> getAll();
}