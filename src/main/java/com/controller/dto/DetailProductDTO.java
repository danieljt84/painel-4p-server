package com.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.model.DetailProduct;
@JsonInclude(Include.NON_NULL)

public class DetailProductDTO {
	private long id;
	private ProductDTO product;
	private BigDecimal price;
	private LocalDate validity;
	private String ruptura;
	private long stock;

	public DetailProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public DetailProductDTO(DetailProduct detail) {
		this.id = detail.getId();
		this.price = detail.getPrice();
		this.validity = detail.getValidity();
		this.ruptura = detail.getRuptura();
		this.stock = detail.getStock();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getValidity() {
		return validity;
	}

	public void setValidity(LocalDate validity) {
		this.validity = validity;
	}

	public String getRuptura() {
		return ruptura;
	}

	public void setRuptura(String ruptura) {
		this.ruptura = ruptura;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}
}
