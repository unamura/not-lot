package com.not.core.dto;

import java.util.List;

public class SearchOpenLibrary {
	
	private Integer numFound;
	private Integer num_found;
	private Integer start;
	private Boolean numFoundExact;
	private String q;
	private String offset;
	
	private List<Doc> docs;


	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Boolean getNumFoundExact() {
		return numFoundExact;
	}
	public void setNumFoundExact(Boolean numFoundExact) {
		this.numFoundExact = numFoundExact;
	}
	public List<Doc> getDocs() {
		return docs;
	}
	public void setDocs(List<Doc> docs) {
		this.docs = docs;
	}
	public Integer getNumFound() {
		return numFound;
	}
	public void setNumFound(Integer numFound) {
		this.numFound = numFound;
	}
	public Integer getNum_found() {
		return num_found;
	}
	public void setNum_found(Integer num_found) {
		this.num_found = num_found;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}

	@Override
	public String toString() {
		return "{\n" + this.num_found + ", " + this.numFound + ", " + this.offset + ", " +
	this.q + ", " + this.numFoundExact + ", " + this.start + ", " + this.docs + "\n}";
	}
}
