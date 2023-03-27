package com.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controller.dto.DetailProductDTO;
import com.controller.dto.detail.ResumeStockDTO;
import com.model.DetailProduct;
import com.repository.DetailProductRepository;

@Service
public class DetailProductService {

	@Autowired
	private DetailProductRepository detailProductRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<DetailProduct> getDetailProductByDataFile(Long idDataFile) {
		List<DetailProduct> detailProducts = new ArrayList<>();
		for (Long id : detailProductRepository.getDetailProductByDataFile(idDataFile)) {
			detailProducts.add(detailProductRepository.findById(id).get());
		}
		return detailProducts;
	}

	public List<String[]> getRupturaBetweenDateByBrand(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand,
			List<Long> idsProject) {
		return detailProductRepository.getRupturaBetweenDateByBrand(initialDate, finalDate, idsBrand,
				(idsProject != null) ? idsProject : new ArrayList<>());
	}

	public List<String[]> getValidityBetweenDateByBrand(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand,
			List<Long> idsProject) {
		LocalDate validityFinalDate = finalDate.plusMonths(1);
		return detailProductRepository.getValidityBetweenDateByBrand(finalDate, initialDate, idsBrand,
				(idsProject != null) ? idsProject : new ArrayList<>(), validityFinalDate);
	}

	public List<ResumeStockDTO> getResumeStock(List<Long> idsBrand, List<Long> idsProject) {
		try {
			List<String[]> datas = detailProductRepository.getResumeStockByBrand(idsBrand,
					(idsProject != null) ? idsProject : new ArrayList<>());
			List<ResumeStockDTO> dtos = new ArrayList<>();
			for (String[] data : datas) {
				Optional<ResumeStockDTO> optional;
				if(data[2].equals("ASSAÍ - PILARES - AVENIDA DOM HÉLDER CÂMARA 6350") && data[4].equals("FIXO_RJ") && data[0].equals("269")) {
					System.out.println("ok");
				}
				try {
					
					optional = dtos.stream().filter(dto -> dto.getNameShop().equals(data[2])
							&& dto.getNameProject().equals(data[4]) && dto.getProduct().equals(data[0])).findAny();
					;
				}catch (Exception e) {
					continue;
				}
				
				ResumeStockDTO resumeStockDTO;
				if (optional.isPresent()) {
					resumeStockDTO = optional.get();
					if(resumeStockDTO.getResume()==null) 				resumeStockDTO.setResume(new HashMap<>());
				} else {
					resumeStockDTO = new ResumeStockDTO();
					resumeStockDTO.setNameShop(data[2]);
					resumeStockDTO.setNameProject(data[4]);
					resumeStockDTO.setProduct(data[0]);
					resumeStockDTO.setResume(new HashMap<>());
				}
				resumeStockDTO.getResume().put(data[6], Long.parseLong(data[7]));
				dtos.add(resumeStockDTO);
			}
			return dtos;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<DetailProductDTO> convertToDTOS(List<DetailProduct> datas) {
		return datas.stream().map(element -> modelMapper.map(element, DetailProductDTO.class))
				.collect(Collectors.toList());
	}

}
