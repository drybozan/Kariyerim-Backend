package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.JobAdFavorites;

import java.util.List;




public interface JobAdFavoritesService {
	public DataResult<List<JobAdFavorites>> getByCandidateId(int candidateId);
    public Result addFavorite(int candidateId, int jobAdId);
    public Result removeFavorite(int favoriteId);
}
