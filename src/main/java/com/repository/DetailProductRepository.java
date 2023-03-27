package com.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.DetailProduct;

import lombok.val;

@Repository
public interface DetailProductRepository extends JpaRepository<DetailProduct, Long>{
  
	@Query(value = "select d.id from detailproduct d ,datafile d2, datafile_detail_products dd "
			+ "where d2.id = dd.data_file_id  and dd.detail_products_id = d.id and d2.id= :idDatafile ",nativeQuery = true)
	List<Long> getDetailProductByDataFile(@Param(value = "idDatafile") Long idDataFile);
	
	@Query(value = "SELECT * "
			+ "FROM report.ruptura r "
			+ "where r.brand_id in :idsBrand "
			+ "and r.date >= :initialDate and r.date <= :finalDate "
			+ "and (CASE WHEN COALESCE(:idsProject,null) is not null THEN r.project_id IN (:idsProject) ELSE true END) ", nativeQuery = true)
	List<String[]> getRupturaBetweenDateByBrand(@Param(value = "initialDate") LocalDate initialDate, @Param(value = "finalDate") LocalDate finalDate,@Param(value = "idsBrand")List<Long> idsBrand, @Param(value ="idsProject") List<Long> idsProject );

	
	@Query(value = "select * "
			+ "FROM report.validity v "
			+ "where v.id_brand in :idsBrand "
			+ "and v.validity >= :finalDate and v.validity <= :validityFinalDate  and v.stock > 0 "
			+ "and v.\"data\" >= :initialDate "
			+ "and (CASE WHEN COALESCE(:idsProject,null) is not null THEN v.id_project IN (:idsProject) ELSE true END) ", nativeQuery = true)
	List<String[]> getValidityBetweenDateByBrand(@Param(value = "finalDate") LocalDate finalDate, @Param(value = "initialDate") LocalDate initialDate,@Param(value = "idsBrand")List<Long> idsBrand, @Param(value ="idsProject") List<Long> idsProject, @Param(value = "validityFinalDate") LocalDate validityFinalDate);

	@Query(value = "select * "
			+ "FROM report.pq p "
			+ "where p.brand_id in :idsBrand and p.seqnum <=4"
			+ "and (CASE WHEN COALESCE(:idsProject,null) is not null THEN p.project_id IN (:idsProject) ELSE true END) ", nativeQuery = true)
	List<String[]> getResumeStockByBrand(@Param(value = "idsBrand")List<Long> idsBrand, @Param(value ="idsProject") List<Long> idsProject);
}
