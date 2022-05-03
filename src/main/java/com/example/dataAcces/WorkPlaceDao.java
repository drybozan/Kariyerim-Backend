package com.example.dataAcces;

import com.example.entities.concretes.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkPlaceDao extends JpaRepository<WorkPlace,Integer>{

}
