package com.example.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","jobAds"})
public class Employer extends User{

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "website")
    private String webSite;

  

    @Column(name = "phone_number")
    private String phoneNumber;

  

    @OneToMany(mappedBy = "employer")
    private List<JobAd> jobAds;
}