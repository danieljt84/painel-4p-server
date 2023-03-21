package com.controller.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)

public class ProductDTO {
	
	private Long id;
	private String name;
	private BrandDTO brand;
	private BigDecimal pricePattern;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BrandDTO getBrand() {
		return brand;
	}
	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}
	public BigDecimal getPricePattern() {
		return pricePattern;
	}
	public void setPricePattern(BigDecimal pricePattern) {
		this.pricePattern = pricePattern;
	}
	
	
}
