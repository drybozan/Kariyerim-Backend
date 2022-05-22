package com.example.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cv")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "github")
    private String github;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "biography")
    private String biography;



    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cv")
    private List<Experiance> experiances;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cv")
    private List<Language> languages;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cv")
    private List<School> schools;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cv")
    private List<Technology> technologies;
}
