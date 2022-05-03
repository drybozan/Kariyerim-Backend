package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Cv;

import java.util.List;




public interface CvService {
    public Result add(int candidateId);
    public DataResult<List<Cv>> getAll();

    public DataResult<Cv> getByCvId(int cvId);
    public DataResult<Cv> getByCandidateId(int candidateId);

    public Result updateGithub(String githublink, int cvId);
    public Result deleteGithub(int cvId);

    public Result updateLinkedin(String linkedinlink, int cvId);
    public Result deleteLinkedin(int cvId);

    public Result updateBiography(String biography, int cvId);
    public Result deleteBiography(int cvId);
}