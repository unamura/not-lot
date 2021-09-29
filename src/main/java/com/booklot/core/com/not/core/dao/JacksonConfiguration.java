package com.not.core.dao;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

	/*
	@Bean
	CommandLineRunner runnerFromFile() {
		
		System.out.println("runnerFromFile()");
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<BookJackson>> typeReference = new TypeReference<List<BookJackson>>(){};
			//InputStream inputStream = TypeReference.class.getResourceAsStream("src/test/resources/book.json");
			File file = new File("src/test/resources/book.json");
			try {
				List<BookJackson> books = mapper.readValue(file, typeReference);
				//System.out.println("books = " + books);
			} catch(IOException e) {
				System.out.println("Unable to get/save the books: " + e.getMessage());
			}
		};
	}
	
	@Bean
	CommandLineRunner runnerJackson() {
		
		System.out.println("runnerJackson()");
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			URL url = new URL("http://openlibrary.org/search.json?author=tolkien");
			
			try {
				SearchOpenLibrary openLibrary = mapper.readValue(url, SearchOpenLibrary.class);
				//System.out.println("OpenLibrary = " + openLibrary);
			} catch(IOException e) {
				System.out.println("Unable to get/save the page: " + e.getMessage());
			}
		};
	}
	
	@Bean
	CommandLineRunner runnerGson() {
		System.out.println("runnerGson()");
		return args -> {
			URL url = new URL("http://openlibrary.org/search.json?author=tolkien");
			InputStreamReader reader = new InputStreamReader(url.openStream());
			SearchOpenLibrary sol = new Gson().fromJson(reader, SearchOpenLibrary.class);
			//System.out.println("sol\n" + sol);
		};
	}
	
	@Bean
	CommandLineRunner runnerJsonOrg() {
		System.out.println("runnerJsonOrg()");
		
		return args -> {
			URL url = new URL("http://openlibrary.org/search.json?author=tolkien");
			JSONTokener tokener = new JSONTokener(url.openStream());
			JSONObject jsonObject = new JSONObject(tokener);
			Integer start = jsonObject.getInt("start");
			System.out.println("jsonObject\n " + start);
		};
	}*/
}
