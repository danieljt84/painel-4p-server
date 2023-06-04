package com.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.form.FilterForm;
import com.model.Brand;
import com.model.DataFile;
import com.model.Photo;
import com.model.Project;
import com.model.Promoter;
import com.model.Shop;

@Repository
public class DataFileRepositoryImp {

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	PhotoRepositoryImp photoRepositoryImp;
	@Autowired
	DetailRepositoryImp detailRepositoryImp;

	// Funcao que busca apenas datafile com photos
	public List<Object> findByBrandwithOnlyPhotos(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand,
			FilterForm filter,Long limit, Long offset) {

	
		String sql = "select distinct  d.id as id_datafile ,b.id as id_brand ,d.shop_id as id_shop ,d.data as date ,pr.id as id_project, d.promoter_id as id_promoter "
				+ "FROM report.datafile d " 
				+ "inner join report.datafile_photos dp on dp.datafile_id = d.id "
				+ "inner join report.photo p on p.id = dp.photos_id "
				+ "inner join operation.brand b on d.brand_id = b.id "
				+ "inner join operation.shop s on d.shop_id = s.id "
				+ "inner join operation.project pr on d.project_id = pr.id "
				+ "inner join operation.promoter p2 on p2.id = d.promoter_id "
				+ "where b.id in (:idsBrand) and d.data >= :initialDate and d.data <= :finalDate "
				+ " and (CASE WHEN COALESCE(:shops,null) is not null THEN s.id IN (:shops) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:projects,null) is not null THEN pr.id IN (:projects) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:products,null) is not null THEN p2.id IN (:products) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:promoters,null) is not null THEN p.id IN (:promoters) ELSE true END)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("idsBrand", idsBrand);
		query.setParameter("initialDate", initialDate);
		query.setParameter("finalDate", finalDate);
		query.setParameter("promoters", (filter != null && filter.getPromoters() != null) ? filter.getPromoters() : new ArrayList<>());
		query.setParameter("shops", (filter != null && filter.getShops() != null) ? filter.getShops() :  new ArrayList<>());
		query.setParameter("products", (filter != null && filter.getProducts() != null) ? filter.getProducts() :  new ArrayList<>());
		query.setParameter("projects", (filter != null && filter.getProjects() != null) ? filter.getProjects() :  new ArrayList<>());


		return query.setMaxResults(limit.intValue()).setFirstResult(offset.intValue()).getResultList();
	}
	
	public Integer findByBrandwithOnlyPhotosCount(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand,
			FilterForm filter,Long limit, Long offset) {

	
		String sql = "select distinct  count(dp.photos_id)"
				+ "FROM report.datafile d " 
				+ "inner join report.datafile_photos dp on dp.datafile_id = d.id "
				+ "inner join report.photo p on p.id = dp.photos_id "
				+ "inner join operation.brand b on d.brand_id = b.id "
				+ "inner join operation.shop s on d.shop_id = s.id "
				+ "inner join operation.project pr on d.project_id = pr.id "
				+ "inner join operation.promoter p2 on p2.id = d.promoter_id "
				+ "where b.id in (:idsBrand) and d.data >= :initialDate and d.data <= :finalDate "
				+ " and (CASE WHEN COALESCE(:shops,null) is not null THEN s.id IN (:shops) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:projects,null) is not null THEN pr.id IN (:projects) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:products,null) is not null THEN p2.id IN (:products) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:promoters,null) is not null THEN p.id IN (:promoters) ELSE true END)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("idsBrand", idsBrand);
		query.setParameter("initialDate", initialDate);
		query.setParameter("finalDate", finalDate);
		query.setParameter("promoters", (filter != null && filter.getPromoters() != null) ? filter.getPromoters() : new ArrayList<>());
		query.setParameter("shops", (filter != null && filter.getShops() != null) ? filter.getShops() :  new ArrayList<>());
		query.setParameter("products", (filter != null && filter.getProducts() != null) ? filter.getProducts() :  new ArrayList<>());
		query.setParameter("projects", (filter != null && filter.getProjects() != null) ? filter.getProjects() :  new ArrayList<>());


		return ((BigInteger) query.getSingleResult()).intValue();
	}

	public List<Object> findByBrandwithOnlyPhotosToBook(LocalDate initialDate, LocalDate finalDate,List<Long> idsBrand,
			FilterForm filter) {

		String sql = "select distinct  d.id as id_datafile ,s.name as nameShop ,d.data as date ,pr.name as id_project, p2.name as namePromoter "
				+ "FROM report.datafile d " 
				+ "inner join report.datafile_photos dp on dp.datafile_id = d.id "
				+ "inner join report.photo p on p.id = dp.photos_id "
				+ "inner join operation.brand b on d.brand_id = b.id "
				+ "inner join operation.shop s on d.shop_id = s.id "
				+ "inner join operation.project pr on d.project_id = pr.id "
				+ "inner join operation.promoter p2 on p2.id = d.promoter_id where b.id in (:idsBrand) and d.data >= :initialDate and d.data <= :finalDate "
				+ " and (CASE WHEN COALESCE(:shops,null) is not null THEN s.id IN (:shops) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:projects,null) is not null THEN pr.id IN (:projects) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:products,null) is not null THEN p2.id IN (:products) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:promoters,null) is not null THEN p.id IN (:promoters) ELSE true END)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("idsBrand", idsBrand);
		query.setParameter("initialDate", initialDate);
		query.setParameter("finalDate", finalDate);
		query.setParameter("promoters", (filter != null && filter.getPromoters() != null) ? filter.getPromoters() : new ArrayList<>());
		query.setParameter("shops", (filter != null && filter.getShops() != null) ? filter.getShops() :  new ArrayList<>());
		query.setParameter("products", (filter != null && filter.getProducts() != null) ? filter.getProducts() :  new ArrayList<>());
		query.setParameter("projects", (filter != null && filter.getProjects() != null) ? filter.getProjects() :  new ArrayList<>());

		return query.getResultList();
	}

	public List<Object> findByBrandwithOnlyDetails(LocalDate initialDate, LocalDate finalDate, List<Long> idsBrand,
			FilterForm filter) throws Exception {
		String sql = "SELECT distinct d.id as id_datafile ,d.brand_id as id_brand ,d.shop_id as id_shop ,d.data as _date ,pr.id as id_project, d.promoter_id as id_promoter\r\n"
				+ "				 FROM datafile d, operation.shop s, operation.promoter p, detailproduct d2 , datafile_detailproducts dd, product p2, operation.project pr\r\n"
				+ "				 where d.brand_id in :idsBrand and d.shop_id = s.id and d.promoter_id = p.id\r\n"
				+ "				 and d.id = dd.datafile_id  and dd.detailproduct_id = d2.id and d2.product_id = p2.id and pr.id = d.project_id\r\n"
				+ "				 and d.data >= :initialDate and d.data <= :finalDate\r\n"
				+ "				 and (CASE WHEN COALESCE(:shops,null) is not null THEN s.id IN (:shops) ELSE true END)\r\n"
				+ "				 and (CASE WHEN COALESCE(:projects,null) is not null THEN pr.id IN (:projects) ELSE true END)\r\n"
				+ "				 and (CASE WHEN COALESCE(:products,null) is not null THEN p2.id IN (:products) ELSE true END)\r\n"
				+ "				 and (CASE WHEN COALESCE(:promoters,null) is not null THEN p.id IN (:promoters) ELSE true END)";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("idsBrand", idsBrand);
		query.setParameter("initialDate", initialDate);
		query.setParameter("finalDate", finalDate);
		query.setParameter("promoters", (filter != null && filter.getPromoters() != null) ? filter.getPromoters() : new ArrayList<>());
		query.setParameter("shops", (filter != null && filter.getShops() != null) ? filter.getShops() :  new ArrayList<>());
		query.setParameter("products", (filter != null && filter.getProducts() != null) ? filter.getProducts() :  new ArrayList<>());
		query.setParameter("projects", (filter != null && filter.getProjects() != null) ? filter.getProjects() :  new ArrayList<>());

		return query.getResultList();
	}

	public List<Object> findByBrandwithOnlyDetailsToDownload(LocalDate initialDate, LocalDate finalDate, List<Long> idBrand,
			FilterForm filter) throws Exception {
		Map<String, List<Object>> objects = new HashMap<>();
		String sql = "SELECT distinct d.id, d.data as date, s.name as shop ,pr.name as project "
				+ " FROM datafile d, operation.shop s, operation.promoter p, detailproduct d2 , datafile_detailproducts dd, product p2, operation.project pr "
				+ " where d.brand_id in :idsBrand and d.shop_id = s.id and d.promoter_id = p.id "
				+ " and d.id = dd.datafile_id  and dd.detailproduct_id = d2.id and d2.product_id = p2.id and pr.id = d.project_id"
				+ " and d.data >= :initialDate and d.data <= :finalDate"
				+ " and (CASE WHEN COALESCE(:shops,null) is not null THEN s.id IN (:shops) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:projects,null) is not null THEN pr.id IN (:projects) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:products,null) is not null THEN p2.id IN (:products) ELSE true END)"
				+ " and (CASE WHEN COALESCE(:promoters,null) is not null THEN p.id IN (:promoters) ELSE true END)";


		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("idsBrand", idBrand);
		query.setParameter("initialDate", initialDate);
		query.setParameter("finalDate", finalDate);
		query.setParameter("finalDate", finalDate);
		query.setParameter("promoters", (filter != null && filter.getPromoters() != null) ? filter.getPromoters() : new ArrayList<>());
		query.setParameter("shops", (filter != null && filter.getShops() != null) ? filter.getShops() :  new ArrayList<>());
		query.setParameter("products", (filter != null && filter.getProducts() != null) ? filter.getProducts() :  new ArrayList<>());
		query.setParameter("projects", (filter != null && filter.getProjects() != null) ? filter.getProjects() :  new ArrayList<>());


		return query.getResultList();
	}

	private String changeSQLString(String sql, Map<String, String[]> filter) {
		String return_sql = "";
		if (filter != null) {
			if (filter.containsKey("shop")) {
				return_sql = return_sql + " or s.name in ( :shops ) ";
			}
			if (filter.containsKey("promoter")) {
				return_sql = return_sql + " or p.name in ( :promoters ) ";
			}
			if (filter.containsKey("product")) {
				return_sql = return_sql + " or p2.name in ( :products ) ";
			}
			if (filter.containsKey("project")) {
				return_sql = return_sql + " or d.project in ( :projects ) ";
			}
			if (!return_sql.equals("")) {
				return_sql = " and ( false " + return_sql + ")";
			}
		}
		return return_sql;
	}

	private void changeQueryParameter(Query query, Map<String, String[]> filter) {
		if (filter.containsKey("shop")) {
			query.setParameter("shops", Arrays.asList(filter.get("shop")));
		}
		if (filter.containsKey("promoter")) {
			query.setParameter("promoters", Arrays.asList(filter.get("promoter")));
		}
		if (filter.containsKey("product")) {
			query.setParameter("products", Arrays.asList(filter.get("product")));
		}
		if (filter.containsKey("project")) {
			query.setParameter("projects", Arrays.asList(filter.get("project")).stream().map(x -> Integer.parseInt(x))
					.collect(Collectors.toList()));
		}
	}
}
