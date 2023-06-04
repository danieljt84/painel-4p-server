package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.FilterService;
import com.service.security.TokenService;
import com.util.LocalDateConverter;

@RestController
@RequestMapping("/filter")
public class FilterController {

	@Autowired
	FilterService filterService;
	@Autowired
	TokenService tokenService;
	
	@GetMapping("/datatable")
	@Cacheable("datatable")
	public ResponseEntity getAllValuesPossibleToFilter(@RequestParam String initialDate, @RequestParam  String finalDate,@RequestParam List<Long> idsBrand,@RequestHeader("Authorization") String token){
		try {
			//tokenService.getIdUsuario(token);
			return ResponseEntity.status(HttpStatus.OK).body(filterService.getAllValuesPossibleToFilterToDataTable(LocalDateConverter.convertToLocalDate(initialDate), LocalDateConverter.convertToLocalDate(finalDate), idsBrand));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/gallery")
	@Cacheable("gallery")
	public ResponseEntity getAllValuesPossibleToFilterToGallery(@RequestParam String initialDate, @RequestParam  String finalDate,@RequestParam List<Long> idsBrand){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(filterService.getAllValuesPossibleToFilterToGallery(LocalDateConverter.convertToLocalDate(initialDate), LocalDateConverter.convertToLocalDate(finalDate), idsBrand));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
}
