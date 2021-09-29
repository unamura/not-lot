package com.not.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.not.core.dao.JacksonFeignClient;
import com.not.core.dto.Doc;
import com.not.core.dto.SearchOpenLibrary;

@RestController
public class JacksonController {

	@Autowired
	private JacksonFeignClient jfclient;

	@RequestMapping(value = "/ns")
	public List<Doc> inputStreamLoop(@RequestParam(defaultValue = "10") int numSt,
			@RequestParam(defaultValue = "20") int numEl, @RequestParam(defaultValue = "not") String order)
			throws MalformedURLException, IOException {
		System.out.println(numSt + " " + numEl + " " + order);

		InputStream is = new URL("http://openlibrary.org/search.json?author=tolkien").openStream();
		ObjectMapper mapper = new ObjectMapper();
		SearchOpenLibrary sol = mapper.readValue(is, new TypeReference<SearchOpenLibrary>() {
		});

		// bl = true;
		// order = "";
		List<Doc> resList = new ArrayList<Doc>();
		Comparator<Doc> com = Comparator.comparing(d -> d.getFirst_publish_year());

		resList = sol.getDocs().stream().skip(numSt).limit(numEl).collect(Collectors.toList());

		if (resList != null) {
			// if (bl) {
			if (order.equals("asc"))
				resList.sort(com);
			else if (order.equals("desc")) {
				resList.sort(com.reversed());
			}
			// }
		}

		return resList;
	}

	@RequestMapping(value = "/node")
	public List<Doc> jacksonNode() throws MalformedURLException, IOException {

		List<Doc> listNode = new ArrayList<Doc>();
		InputStream is = new URL("http://openlibrary.org/search.json?author=tolkien").openStream();
		ObjectNode node = new ObjectMapper().readValue(is, ObjectNode.class);
		if (node.has("docs")) {

			JsonNode docs = node.get("docs");
			System.out.println("node stream: ");

			listNode = StreamSupport.stream(docs.spliterator(), false).map(nod -> {
				Doc doc = new Doc();
				doc.setTitle(nod.get("title").toString());
				doc.setFirst_publish_year(nod.get("first_publish_year").asInt());
				return doc;
			}).sorted(Comparator.comparing(d -> d.getFirst_publish_year())).collect(Collectors.toList());
			/*
			 * List<Doc> st = StreamSupport.stream(docs.spliterator(), false).map(nod -> {
			 * Doc doc = new Doc(); doc.setTitle(nod.get("title").toString());
			 * doc.setFirst_publish_year(nod.get("first_publish_year").asInt()); return doc;
			 * }).collect(Collectors.toList());
			 * 
			 * Comparator<Doc> com = Comparator.comparing(d -> d.getFirst_publish_year());
			 * 
			 * st.sort(com.reversed()); System.out.println("rev\n:" + st);
			 */
		}
		System.out.println(listNode);
		return listNode;
	}

	@RequestMapping(value = "/is")
	public String inputStream() throws MalformedURLException, IOException {

		InputStream is = new URL("http://openlibrary.org/search.json?author=tolkien").openStream();
		ObjectMapper mapper = new ObjectMapper();
		SearchOpenLibrary sol = mapper.readValue(is, new TypeReference<SearchOpenLibrary>() {
		});

		return "Hi i'm is " + is;
	}

	@RequestMapping("/infinite")
	public List<Integer> infiniteLoop() {
		Stream<Integer> si = Stream.iterate(0, i -> i + 2);
		List<Integer> coll = si.skip(25).limit(35).collect(Collectors.toList());

		return coll;
	}

	@RequestMapping(value = "/rt/listdoc", method = RequestMethod.GET)
	public List<Doc> getBooks() throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		URL url = null;
		List<Doc> docList = new ArrayList<Doc>();
		try {
			url = new URL("http://openlibrary.org/search.json?author=tolkien");
		} catch (MalformedURLException e1) {
			System.out.println("URL error: " + e1.getMessage());
			e1.printStackTrace();
		}
		SearchOpenLibrary sol = mapper.readValue(url, SearchOpenLibrary.class);
		if (sol != null)
			docList = sol.getDocs().stream().filter(doc -> {
				return doc.getFirst_publish_year() < 1940 ? true : false;
			}).collect(Collectors.toList());

		return docList;
	}

	@GetMapping("/rt/status")
	public SearchOpenLibrary readFromUrl() {
		String url = "http://openlibrary.org/search.json?author=tolkien";
		RestTemplate rt = new RestTemplate();
		SearchOpenLibrary sol = rt.getForObject(url, SearchOpenLibrary.class);

		return sol;
	}

	@GetMapping("/rt/feign")
	public SearchOpenLibrary readWithFeign() {
		SearchOpenLibrary sol = jfclient.findSearchOpenLibrary("tolkien");

		return sol;
	}

}
