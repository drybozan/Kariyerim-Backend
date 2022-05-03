package com.example.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateForGetDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
}
