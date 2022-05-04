package com.example.dataAcces;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.concretes.JobAd;
import com.example.entities.dtos.JobAdFilter;



public interface JobAdDao extends JpaRepository<JobAd,Integer> {
   
    @Query("Select j from com.Bozan.db.entities.concretes.JobAd j where ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"
        +" and ((:#{#filter.jobPositionId}) IS NULL OR j.jobPosition.id IN (:#{#filter.jobPositionId}))"
        +" and ((:#{#filter.workPlaceId}) IS NULL OR j.workPlace.id IN (:#{#filter.workPlaceId}))"
        +" and ((:#{#filter.workTimeId}) IS NULL OR j.workTime.id IN (:#{#filter.workTimeId}))"
        )
    public Page<JobAd> getByFilter(@Param("filter") JobAdFilter jobAdFilter, Pageable pageable);
}
