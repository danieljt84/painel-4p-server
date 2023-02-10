package com.controller;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.form.FilterForm;
import com.model.Brand;
import com.model.Photo;
import com.model.Project;
import com.service.BrandService;
import com.service.DataFileService;
import com.service.PdfService;
import com.service.PhotoService;
import com.util.book.DataBook;
import com.util.book.PhotoDataBook;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	PdfService pdfService;
	@Autowired
	DataFileService dataFileService;
	@Autowired
	PhotoService photoService;
	@Autowired
	BrandService brandService;
	
	@PostMapping()
	public ResponseEntity generateBookPhotos(@RequestParam(name = "initialDate",required = false) String initialDate,@RequestParam  String finalDate
			,@RequestParam List<Brand> brands, @RequestBody(required = false)  FilterForm filter) {
		try { 
			List<Long> idBrands=new ArrayList<>();
			for (Brand itemBrands: brands) {
		   idBrands.add(itemBrands.getId());
			}
		   List<Object[]> datas;
		   List<DataBook> datasBook = new ArrayList<>();
		   
		   for (Long idBrand: idBrands) {
			   if(filter!=null) {
		            datas = dataFileService.getPhotosToBook(LocalDate.parse(initialDate), LocalDate.parse(finalDate), idBrand, filter.getFilter()).stream().map(element -> ((Object[]) element)).collect(Collectors.toList());
			   }else {
				   datas = dataFileService.getPhotosToBook(LocalDate.parse(initialDate), LocalDate.parse(finalDate), idBrand, null).stream().map(element -> ((Object[]) element)).collect(Collectors.toList());
			   }
		   }
		   
		   for(Object[] data :datas) {
			   DataBook dataBook = new DataBook();
			   dataBook.setNameShop((String) data[1]);
			   dataBook.setDate(((Date) data[2]).toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		       dataBook.setNameProject(Project.valueOf((Integer)data[3]).get().name());
		       dataBook.setNamePromoter((String) data[4]);
		       List<PhotoDataBook> photoDataBooks = new ArrayList<>();
		       for(Photo photo: photoService.getPhotosByDataFile(((BigInteger)data[0]).longValue())){
		    	   PhotoDataBook photoDataBook =new PhotoDataBook();
		    	   photoDataBook.setUrlImage(photo.getUrl());
		    	   photoDataBook.setSection(photo.getSection());
		    	   photoDataBooks.add(photoDataBook);
		       }
		       dataBook.setPhotoDataBooks(photoDataBooks);
		       datasBook.add(dataBook);
		   }
		   
			var pdf = pdfService.createBookPhotos(datasBook,brand.getName(),LocalDate.parse(initialDate),LocalDate.parse(finalDate));
		    return ResponseEntity.ok(pdf);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);		}
	}

}