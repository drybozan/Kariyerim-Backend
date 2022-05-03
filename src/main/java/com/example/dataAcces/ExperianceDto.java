package com.example.dataAcces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bozan.db.entities.concretes.Experiance;


public interface ExperianceDto extends JpaRepository<Experiance,Integer> {
    List<Experiance> findByCvId(int id);
}
