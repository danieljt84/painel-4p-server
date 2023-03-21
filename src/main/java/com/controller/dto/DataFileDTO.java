package com.controller.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.model.Project;
@JsonInclude(Include.NON_NULL)
public class DataFileDTO {
	private long id;
	private ShopDTO shop;
	private List<PhotoDTO> photos;
	private List<DetailProductDTO> detailProducts; 
	private BrandDTO brand;
	private LocalDate data;
	private PromoterDTO promoter;
	private Project project;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ShopDTO getShop() {
		return shop;
	}
	public void setShop(ShopDTO shop) {
		this.shop = shop;
	}
	public List<PhotoDTO> getPhotos() {
		return photos;
	}
	public void setPhotos(List<PhotoDTO> photos) {
		this.photos = photos;
	}
	public List<DetailProductDTO> getDetail_Products() {
		return detailProducts;
	}
	public void setDetail_Products(List<DetailProductDTO> detail_Products) {
		this.detailProducts = detail_Products;
	}
	public BrandDTO getBrand() {
		return brand;
	}
	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public PromoterDTO getPromoter() {
		return promoter;
	}
	public void setPromoter(PromoterDTO promoter) {
		this.promoter = promoter;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}
