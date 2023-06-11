package com.controller.dto.detail;

import java.time.LocalDate;

public class SupplyDTO {
	
	private String product;
	private String shop;
	private String project;
	private Long idBrand;
	private LocalDate date;
	private Long stock;
	private Long seq;
	public SupplyDTO(String product, String shop, String project, Long idBrand, LocalDate date, Long stock, Long seq) {
		super();
		this.product = product;
		this.shop = shop;
		this.project = project;
		this.idBrand = idBrand;
		this.date = date;
		this.stock = stock;
		this.seq = seq;
	}
	public SupplyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Long getIdBrand() {
		return idBrand;
	}
	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}

}
