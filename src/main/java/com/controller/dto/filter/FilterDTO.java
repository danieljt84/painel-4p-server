package com.controller.dto.filter;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class FilterDTO {
	
	private List<Long> shop;
	private List<Long> product;
	private List<Long> promoter;
	private List<Long> project;
	private List<Long> status;
	
	

	
}
