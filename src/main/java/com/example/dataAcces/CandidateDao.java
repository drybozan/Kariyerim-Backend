package com.example.dataAcces;

import com.example.entities.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;




public interface CandidateDao extends JpaRepository<Candidate,Integer> {
    Candidate findByNationalNumber(String nationalNumber);
    Candidate findByEmail(String email);
    
}
