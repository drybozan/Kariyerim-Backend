package com.example.dataAcces;

import com.example.entities.concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobPositionDao extends JpaRepository<JobPosition,Integer> {
    JobPosition findByName(String name);
}
