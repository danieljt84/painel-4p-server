package com.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.controller.dto.ProductDTO;
import com.controller.dto.ProjectDTO;
import com.controller.dto.PromoterDTO;
import com.controller.dto.ShopDTO;
import com.controller.dto.filter.FilterDTO;
import com.controller.dto.filter.FilterDetailsDTO;
import com.controller.dto.filter.FilterGalleryDTO;
import com.repository.FilterRepositoryImp;
import com.service.security.TokenService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Service
public class FilterService {

	@Autowired
	FilterRepositoryImp filterRepositoryImp;


	// Funcao que organiza os valores para usar no filtro dos campos da Datatable do
	// front-end
	// Adiciona a um Map com a chave de cada valor
	public FilterDetailsDTO getAllValuesPossibleToFilterToDataTable(LocalDate initialDate, LocalDate finalDate,
			List<Long> idsBrand) {
		FilterDetailsDTO filterDetailsDTO = new FilterDetailsDTO();
		filterDetailsDTO.setShop(generateShopDTO(
				filterRepositoryImp.getAllValuesToShopPossibleToFilter(initialDate, finalDate, idsBrand)));
		filterDetailsDTO.setPromoter(generatePromoterDTO(
				filterRepositoryImp.getAllValuesToPromoterPossibleToFilter(initialDate, finalDate, idsBrand)));
		filterDetailsDTO.setProduct(generateProductDTO(
				filterRepositoryImp.getAllValuesToProductPossibleToFilter(initialDate, finalDate, idsBrand)));
		filterDetailsDTO
				.setStatus( filterRepositoryImp.getAllValuesToStatusPossibleToFilter(initialDate, finalDate, idsBrand));
		filterDetailsDTO.setProject(generateProjectDTO(
				filterRepositoryImp.getAllValuesToProjectPossibleToFilter(initialDate, finalDate, idsBrand)));

		return filterDetailsDTO;
	}

	// Funcao que organiza os valores para usar no filtro dos campos da Galeria de
	// fotos do front-end
	// Adiciona a um Map com a chave de cada valor
	public FilterGalleryDTO getAllValuesPossibleToFilterToGallery(LocalDate initialDate, LocalDate finalDate,
			List<Long> idsBrand) {
		FilterGalleryDTO filterGalleryDTO = new FilterGalleryDTO();
		filterGalleryDTO
				.setShop(generateShopDTO(filterRepositoryImp.getAllValuesToShopPossibleToFilter(initialDate, finalDate, idsBrand)));
		filterGalleryDTO.setPromoter(generatePromoterDTO(
				filterRepositoryImp.getAllValuesToPromoterPossibleToFilter(initialDate, finalDate, idsBrand)));
		filterGalleryDTO
				.setProduct(generateProductDTO(filterRepositoryImp.getAllValuesToProductPossibleToFilter(initialDate, finalDate, idsBrand)));
		filterGalleryDTO
				.setSection(filterRepositoryImp.getAllValuesToSectionPossibleToFilter(initialDate, finalDate, idsBrand));
		filterGalleryDTO
				.setProject(generateProjectDTO(filterRepositoryImp.getAllValuesToProjectPossibleToFilter(initialDate, finalDate, idsBrand)));
		return filterGalleryDTO;
	}

	private List<ShopDTO> generateShopDTO(List<Object[]> datas) {
		List<ShopDTO> dtos = new ArrayList<>();
		for (Object[] data : datas) {
			ShopDTO dto = new ShopDTO();
			dto.setId(((BigInteger) data[0]).longValue());
			dto.setName((String) data[1]);
			dtos.add(dto);
		}
		return dtos;
	}

	private List<ProjectDTO> generateProjectDTO(List<Object[]> datas) {
		List<ProjectDTO> dtos = new ArrayList<>();
		for (Object[] data : datas) {
			ProjectDTO dto = new ProjectDTO();
			dto.setId(((BigInteger) data[0]).longValue());
			dto.setName((String) data[1]);
			dtos.add(dto);
		}
		return dtos;
	}

	private List<PromoterDTO> generatePromoterDTO(List<Object[]> datas) {
		List<PromoterDTO> dtos = new ArrayList<>();
		for (Object[] data : datas) {
			PromoterDTO dto = new PromoterDTO();
			dto.setId(((BigInteger) data[0]).longValue());
			dto.setName((String) data[1]);
			dtos.add(dto);
		}
		return dtos;
	}

	private List<ProductDTO> generateProductDTO(List<Object[]> datas) {
		List<ProductDTO> dtos = new ArrayList<>();
		for (Object[] data : datas) {
			ProductDTO dto = new ProductDTO();
			dto.setId(((BigInteger) data[0]).longValue());
			dto.setName((String) data[1]);
			dtos.add(dto);
		}
		return dtos;
	}

}
