package com.controller.dto.filter;

import java.util.List;

import com.controller.dto.ProductDTO;
import com.controller.dto.ProjectDTO;
import com.controller.dto.PromoterDTO;
import com.controller.dto.ShopDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class FilterDetailsDTO {
	private List<ShopDTO> shop;
	private List<PromoterDTO> promoter;
	private List<ProductDTO> product;
	private List<Object> status;
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
	public List<Object> getStatus() {
		return status;
	}
	public void setStatus(List<Object> status) {
		this.status = status;
	}
	public List<ProjectDTO> getProject() {
		return project;
	}
	public void setProject(List<ProjectDTO> project) {
		this.project = project;
	}
}
