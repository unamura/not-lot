package com.not.core.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.not.core.dto.Doc;
import com.not.core.dto.SearchOpenLibrary;

@Component
public class RetrieveDataFromOL {
	
	public SearchOpenLibrary getAuthorData() throws MalformedURLException, IOException {
		
		InputStream is = new URL("http://openlibrary.org/search.json?author=tolkien").openStream();
		ObjectMapper mapper = new ObjectMapper();
		SearchOpenLibrary sol = mapper.readValue(is, new TypeReference<SearchOpenLibrary>(){});
		
		return sol;
	}
	
	public Stream<Doc> getDocStream() throws MalformedURLException, IOException {
		
		SearchOpenLibrary sol = getAuthorData();		
		Stream<Doc> resList = sol.getDocs().stream();
		
		return resList;
	}

}
