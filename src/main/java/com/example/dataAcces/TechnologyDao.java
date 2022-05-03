package com.example.dataAcces;

import com.example.entities.concretes.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TechnologyDao extends JpaRepository<Technology,Integer> {
    List<Technology> findByCvId(int id);
}
