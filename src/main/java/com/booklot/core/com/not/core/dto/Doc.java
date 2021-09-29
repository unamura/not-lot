package com.not.core.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Doc {

	private Long id;
	private String title;
	private Integer first_publish_year;
	private List<String> author_name;
	/*
	 * private List<String> text; private String type; private List<String> seed;
	 * private String title_suggest; private Boolean has_fulltext; private Integer
	 * edition_count; private List<String> edition_key; private List<String>
	 * publish_date; private List<Integer> publish_year; private Integer
	 * first_publish_year; private List<String> lccn; private List<String>
	 * publish_place; private List<String> oclc; private List<String> contributor;
	 * private List<String> lcc; private List<String> ddc; private List<String>
	 * isbn; private Integer last_modified_i; private Integer ebook_count_i; private
	 * String cover_edition_key; private Integer cover_i; private List<String>
	 * publisher; private List<String> language; private List<String> author_key;
	 * private List<String> author_name; private List<String> subject; private
	 * List<String> id_goodreads; private List<String> id_librarything; private
	 * List<String> publisher_facet; private List<String> subject_facet; private
	 * Double _version_; private String lcc_sort; private List<String> author_facet;
	 * private List<String> subject_key;
	 */
	// private String ddc_sort;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * public String getDdc_sort() { return ddc_sort; }
	 * 
	 * public void setDdc_sort(String ddc_sort) { this.ddc_sort = ddc_sort; }
	 */

	@Override
	public String toString() {
		return "\n{" + this.title + ", " + this.author_name + ", " + this.first_publish_year + "}";
	}

	public Integer getFirst_publish_year() {
		return first_publish_year;
	}

	public void setFirst_publish_year(Integer first_publish_year) {
		this.first_publish_year = first_publish_year;
	}

	public List<String> getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(List<String> author_name) {
		this.author_name = author_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
