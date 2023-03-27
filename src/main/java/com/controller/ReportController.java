package com.controller;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.form.FilterForm;
import com.model.DetailProduct;
import com.model.Project;
import com.service.DataFileService;
import com.service.DetailProductService;
import com.service.ExcelService;
import com.util.LocalDateConverter;

@RequestMapping("/report")
@RestController
public class ReportController {
	
	@Autowired
	DataFileService dataFileService;
	@Autowired
	DetailProductService detailProductService;
	@Autowired
	ExcelService excelService;
	
	
	@RequestMapping(value="/details", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity listDetailsToDownload(@RequestParam(name ="initialDate") String initialDate,@RequestParam  String finalDate,@RequestParam(name = "idsBrand") List<Long> idsBrand
			, @RequestBody(required = false)  FilterForm filter) {
        HttpHeaders headers = new HttpHeaders();
		List<String[]> datas_excel = new ArrayList<>();

        try {
        	List<Object> datas;
        	
        		datas = dataFileService.getDetailsToDownload(LocalDateConverter.convertToLocalDate(initialDate) , LocalDateConverter.convertToLocalDate(finalDate)
        				,idsBrand,filter);

    		for(Object object: datas) {
    			Object[] cast = (Object[]) object;
    		    var date = ((java.sql.Date) cast[1]).toLocalDate().toString();
                var shop = (String) cast[2];
                var project = ((String)cast[3]);
                List<DetailProduct> details = detailProductService.getDetailProductByDataFile(((BigInteger)cast[0]).longValue());
                for(DetailProduct detail: details){
        			String[] data = new String[8];
                	data[0] = date.toString();
                	data[1] = shop;
                	data[2] = project;
                	data[3] = detail.getProduct().getName();
                	data[4] = (detail.getPrice()!=null)? detail.getPrice().toString() : null;
                	data[5] = (detail.getStock()!=null)? Long.toString(detail.getStock()) : null;
                    data[6] = (detail.getValidity()!=null)? detail.getValidity().toString(): null;
                    data[7] = detail.getRuptura();
                    datas_excel.add(data);
                }            
    		}
    		return ResponseEntity
            		.ok()
            		.headers(headers)
            		.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            		.body(excelService.generateDetailsProductExcel(datas_excel));
        }catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/ruptura")
	public ResponseEntity rupturaToDownload(@RequestParam(name ="initialDate",required = false) String initialDate,@RequestParam  String finalDate, @RequestParam(name = "idsBrand") List<Long> idsBrand, @RequestParam(name = "idsProject",required = false) List<Long> idsProject) {
		try{
	        HttpHeaders headers = new HttpHeaders();

			List<String[]> datas = detailProductService.getRupturaBetweenDateByBrand( LocalDateConverter.convertToLocalDate(initialDate),  LocalDateConverter.convertToLocalDate(finalDate),idsBrand,idsProject);
			return ResponseEntity
            		.ok()
            		.headers(headers)
            		.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            		.body(excelService.generateRupturaExcel(datas));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/validity")
	public ResponseEntity validadeToDownload(@RequestParam(name ="initialDate",required = false) String initialDate,@RequestParam  String finalDate, @RequestParam(name = "idsBrand") List<Long> idsBrand, @RequestParam(name = "idsProject",required = false) List<Long> idsProject) {
		try{
	        HttpHeaders headers = new HttpHeaders();
			List<String[]> datas = detailProductService.getValidityBetweenDateByBrand(LocalDateConverter.convertToLocalDate(initialDate),  LocalDateConverter.convertToLocalDate(finalDate),idsBrand,idsProject);
			if(datas==null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
			
			var in = new InputStreamResource(new ByteArrayInputStream(excelService.generateValidadeExcel(datas)));
			return ResponseEntity
            		.ok()
            		.headers(headers)
            		.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            		.body(in);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
