package com.controller.dto.detail;

import java.util.List;

public class SupplyListDTO {
	
	private Long count;
	private List<SupplyDTO> supplyList;
	
	
	public SupplyListDTO(Long count, List<SupplyDTO> supplyList) {
		super();
		this.count = count;
		this.supplyList = supplyList;
	}
	
	
	public SupplyListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}


	public List<SupplyDTO> getSupplyList() {
		return supplyList;
	}


	public void setSupplyList(List<SupplyDTO> supplyList) {
		this.supplyList = supplyList;
	}
	
}
