package com.controller.dto.detail;

import java.util.List;
import java.util.Map;

public class ResumeStockDTO {
	
	private String nameShop;
	private String nameProject;
	private String product;
	Map<String, Long>resume;



	public Map<String, Long> getResume() {
		return resume;
	}

	public void setResume(Map<String, Long> resume) {
		this.resume = resume;
	}

	public String getNameShop() {
		return nameShop;
	}

	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}

	public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	

}
