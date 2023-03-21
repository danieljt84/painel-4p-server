package com.controller.dto.filter;

import java.util.List;

import com.controller.dto.ProductDTO;
import com.controller.dto.ProjectDTO;
import com.controller.dto.PromoterDTO;
import com.controller.dto.ShopDTO;

public class FilterGalleryDTO {
	private List<ShopDTO> shop;
	private List<PromoterDTO> promoter;
	private List<ProductDTO> product;
	private List<Object> section;
	private List<ProjectDTO> project;
	
	public List<ShopDTO> getShop() {
		return shop;
	}
	public void setShop(List<ShopDTO> shop) {
		this.shop = shop;
	}
	public List<PromoterDTO> getPromoter() {
		return promoter;
	}
	public void setPromoter(List<PromoterDTO> promoter) {
		this.promoter = promoter;
	}
	public List<ProductDTO> getProduct() {
		return product;
	}
	public void setProduct(List<ProductDTO> product) {
		this.product = product;
	}
	public List<Object> getSection() {
		return section;
	}
	public void setSection(List<Object> section) {
		this.section = section;
	}
	public List<ProjectDTO> getProject() {
		return project;
	}
	public void setProject(List<ProjectDTO> project) {
		this.project = project;
	}

	
}
