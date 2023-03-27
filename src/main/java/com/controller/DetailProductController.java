package com.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controller.dto.detail.RupturaByBrandDTO;
import com.controller.dto.detail.ValidityByBrandDTO;
import com.service.DetailProductService;
import com.util.LocalDateConverter;

@RestController
@RequestMapping("/detail")
public class DetailProductController {
	
	@Autowired
	DetailProductService detailProductService;
	
	@GetMapping
	@RequestMapping("/ruptura")
	@Cacheable("ruptura")
	public ResponseEntity getRupturaBetweenDateByBrand(@RequestParam(name ="initialDate",required = false) String initialDate,@RequestParam  String finalDate, @RequestParam(name = "idsBrand") List<Long> idsBrand, @RequestParam(name = "idsProject",required = false) List<Long> idsProject){
		try {
		List<RupturaByBrandDTO> dtos = new ArrayList<>();
		List<String[]> datas = detailProductService.getRupturaBetweenDateByBrand( LocalDateConverter.convertToLocalDate(initialDate),  LocalDateConverter.convertToLocalDate(finalDate),idsBrand,idsProject);
		for(String[] data:datas) {
			RupturaByBrandDTO rupturaByBrandDTO = new RupturaByBrandDTO();
			rupturaByBrandDTO.setNameShop((String)data[0]);
			rupturaByBrandDTO.setNameProduct((String)data[1]);
			rupturaByBrandDTO.setDate(((String)data[2]).toString());
			dtos.add(rupturaByBrandDTO);
		}
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@GetMapping
	@RequestMapping("/validity")
	@Cacheable("validity")
	public ResponseEntity getValidityBetweenDateByBrand(@RequestParam(name ="initialDate",required = false) String initialDate,@RequestParam  String finalDate, @RequestParam(name = "idsBrand") List<Long> idsBrand, @RequestParam(name = "idsProject",required = false) List<Long> idsProject){
		try {
			List<ValidityByBrandDTO> dtos = new ArrayList<>();
			List<String[]> datas = detailProductService.getValidityBetweenDateByBrand(LocalDateConverter.convertToLocalDate(initialDate),  LocalDateConverter.convertToLocalDate(finalDate),idsBrand,idsProject);
			for(String[] data:datas) {
				ValidityByBrandDTO dto = new ValidityByBrandDTO();
				dto.setDate(data[0]);
				dto.setNameProduct(data[3]);
				dto.setNameShop(data[4]);
				dto.setValidity(data[5]);
				dto.setStock(data[6]);
				dtos.add(dto);
			}
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@GetMapping
	@RequestMapping("/resumestock")
	public ResponseEntity getResumeStock (@RequestParam(name = "idsBrand") List<Long> idsBrand, @RequestParam(name = "idsProject",required = false) List<Long> idsProject) {
		return ResponseEntity.status(HttpStatus.OK).body(detailProductService.getResumeStock( idsBrand, idsProject));		
	}
}
