package com.example.dataAcces;

import com.example.entities.concretes.Cv;
import org.springframework.data.jpa.repository.JpaRepository;




public interface CvDao extends JpaRepository<Cv,Integer> {
    Cv findByCandidateId(int id);
}
