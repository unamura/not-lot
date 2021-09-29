package com.not.core.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.not.core.dto.Doc;

@Component
public class OrderForVisual {

	@Autowired
	RetrieveDataFromOL retrieveData;

	public List<Doc> presentListData(Integer numSt, Integer numEl, String order)
			throws MalformedURLException, IOException {

		List<Doc> docList = new ArrayList<Doc>();

		Stream<Doc> docStream = retrieveData.getDocStream();
		System.out.println("Stream: " + docStream);

		if (numSt != null & numEl != null) {
			if ((numSt % 10 == 0 || numSt % 30 == 0) & (numEl == 10 || numEl == 20)) {
				docStream = docStream.skip(numSt).limit(numEl);
			}
		}

		docList = docStream.collect(Collectors.toList());

		if (order != null) {
			Comparator<Doc> com = Comparator.comparing(d -> d.getFirst_publish_year());

			if (order.equals("asc"))
				docList.sort(com);
			else if (order.equals("desc"))
				docList.sort(com.reversed());
		}

		return docList;
	}
}
