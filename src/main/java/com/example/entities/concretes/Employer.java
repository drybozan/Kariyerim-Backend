package com.example.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Employer extends User{

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "website")
    private String webSite;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Transient
    private List<JobAd> jobAds;
}