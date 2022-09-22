package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.FilterService;
import com.util.LocalDateConverter;

@RestController
@RequestMapping("/filter")
public class FilterController {

	@Autowired
	FilterService filterService;
	@GetMapping("/datatable")
	public ResponseEntity getAllValuesPossibleToFilter(@RequestParam String initialDate, @RequestParam  String finalDate,@RequestParam Long brandId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(filterService.getAllValuesPossibleToFilterToDataTable(LocalDateConverter.convertToLocalDate(initialDate), LocalDateConverter.convertToLocalDate(finalDate), brandId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping("/gallery")
	public ResponseEntity getAllValuesPossibleToFilterToGallery(@RequestParam String initialDate, @RequestParam  String finalDate,@RequestParam Long brandId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(filterService.getAllValuesPossibleToFilterToGallery(LocalDateConverter.convertToLocalDate(initialDate), LocalDateConverter.convertToLocalDate(finalDate), brandId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping("/activation")
	public ResponseEntity getAllValuesPossibleToFilterToActivitionCard(@RequestParam String initialDate, @RequestParam  String finalDate,@RequestParam Long brandId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(filterService.getAllValuesPossibleToFilterToActivitionCard(LocalDateConverter.convertToLocalDate(initialDate), LocalDateConverter.convertToLocalDate(finalDate), brandId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	

}
