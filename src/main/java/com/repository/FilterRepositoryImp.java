package com.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.controller.dto.ProductDTO;
import com.controller.dto.ProjectDTO;
import com.controller.dto.PromoterDTO;
import com.controller.dto.ShopDTO;

@Repository
public class FilterRepositoryImp {

	@PersistenceContext
	EntityManager entityManager;


	public List<Object[]> getAllValuesToShopPossibleToFilter(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand) {
    	Query query = entityManager
    			.createNativeQuery("select distinct s.id,s.name  from datafile d,operation.shop s  where d.shop_id = s.id and d.brand_id in :idsBrand and d.\"data\" >= :initialDate and  d.\"data\" <= :finalDate ;");
        query.setParameter("idsBrand", idsBrand);
    	query.setParameter("initialDate", initialDate);
    	query.setParameter("finalDate", finalDate);
    	return query.getResultList();
	}
	
	public List<Object[]> getAllValuesToPromoterPossibleToFilter(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand) {
    	Query query = entityManager
    			.createNativeQuery("select distinct p.id, p.name  from datafile d,operation.promoter p  where d.promoter_id = p.id and d.brand_id in :idsBrand and d.\"data\" >= :initialDate and  d.\"data\" <= :finalDate ;");
        query.setParameter("idsBrand", idsBrand);
    	query.setParameter("initialDate", initialDate);
    	query.setParameter("finalDate", finalDate);
    	return query.getResultList();
	}
	
	public List<Object[]> getAllValuesToProductPossibleToFilter(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand) {
    	Query query = entityManager
    			.createNativeQuery("select distinct p.id, p.\"name\" "
    					+ "from datafile d,product p, datafile_detailproducts dd, detailproduct d2 "
    					+ "where d.id = dd.datafile_id and dd.detailproduct_id= d2.id and d2.product_id = p.id  and d.brand_id in :idsBrand and d.\"data\" >= :initialDate and  d.\"data\" <= :finalDate ;");
        query.setParameter("idsBrand", idsBrand);
    	query.setParameter("initialDate", initialDate);
    	query.setParameter("finalDate", finalDate);
    	return query.getResultList();
	}
	
	public List<Object> getAllValuesToStatusPossibleToFilter(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand) {
    	Query query = entityManager
    			.createNativeQuery("select distinct  d2.ruptura "
    					+ "from datafile d, datafile_detailproducts dd, detailproduct d2  "
    					+ "where d.id = dd.datafile_id and dd.detailproduct_id= d2.id and d.brand_id in :idsBrand and d.\"data\" >= :initialDate and  d.\"data\" <= :finalDate ;");
        query.setParameter("idsBrand", idsBrand);
    	query.setParameter("initialDate", initialDate);
    	query.setParameter("finalDate", finalDate);
    	return query.getResultList();
	}
	
	public List<Object[]> getAllValuesToProjectPossibleToFilter(LocalDate initialDate, LocalDate finalDate,  List<Long> idsBrand) {
    	Query query = entityManager
    			.createNativeQuery("select distinct  pr.id, pr.name "
    					+ "from datafile d, operation.project pr "
    					+ "where d.brand_id in :idsBrand and d.project_id = pr.id  and d.\"data\" >= :initialDate and  d.\"data\" <= :finalDate ;");
        query.setParameter("idsBrand", idsBrand);
    	query.setParameter("initialDate", initialDate);
    	query.setParameter("finalDate", finalDate);
    	return query.getResultList();
	}
	
	public List<Object> getAllValuesToSectionPossibleToFilter(LocalDate initialDate, LocalDate finalDate,  List<Long> idsBrand) {
    	Query query = entityManager
    			.createNativeQuery("select distinct  p.section "
    					+ "from datafile d,photo p, datafile_photos dp "
    					+ "where d.id = dp.datafile_id and dp.photos_id= p.id and d.brand_id in :idsBrand and d.\"data\" >= :initialDate and  d.\"data\" <= :finalDate ;");
        query.setParameter("idsBrand", idsBrand);
    	query.setParameter("initialDate", initialDate);
    	query.setParameter("finalDate", finalDate);
    	return query.getResultList();
	}
	
	


}
