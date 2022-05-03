package com.example.services.abstracts;

import com.example.utilities.results.DataResult;
import com.example.utilities.results.Result;
import com.example.entities.concretes.Language;
import com.example.entities.dtos.LanguageForSetDto;

import java.util.List;



public interface LanguageService {
	 	public Result addLanguage(LanguageForSetDto languageForSetDto);
	    public Result deleteLanguage(int languageId);
	    public DataResult<List<Language>> getByCvId(int cvId);
}
