package com.example.dataAcces;

import com.example.entities.concretes.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkTimeDao extends JpaRepository<WorkTime,Integer> {
}
