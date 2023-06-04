package com.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class DataFileOnlyPhotoListDTO {
	private List<DataFileOnlyPhotoDTO> list;
	private Integer count;
	
	public DataFileOnlyPhotoListDTO() {
		this.list = new ArrayList<>();
	}
	
	public List<DataFileOnlyPhotoDTO> getList() {
		return list;
	}
	public void setList(List<DataFileOnlyPhotoDTO> list) {
		this.list = list;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	

}
