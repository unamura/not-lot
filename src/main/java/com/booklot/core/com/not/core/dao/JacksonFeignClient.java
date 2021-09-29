package com.not.core.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.not.core.dto.SearchOpenLibrary;

@FeignClient(value = "searchopenlibrary", url = "openlibrary.org")
public interface JacksonFeignClient {
	
	@RequestMapping("/search.json")
	public SearchOpenLibrary findSearchOpenLibrary(@RequestParam String author);

}
