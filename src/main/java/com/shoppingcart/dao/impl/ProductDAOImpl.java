package com.shoppingcart.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.dao.ProductDAO;
import com.shoppingcart.entity.Product;
import com.shoppingcart.model.PaginationResult;
import com.shoppingcart.model.ProductInfo;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PaginationResult<ProductInfo> getAllProductInfos(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		Session session = sessionFactory.getCurrentSession();
		// SELECT PRO.code, PRO.name, PRO.price FROM Product PRO
		String hql = "SELECT NEW " + ProductInfo.class.getName() + " (PRO.code, PRO.name, PRO.price) FROM Product PRO";

		if (likeName != null && likeName.length() > 0) {
			hql += " WHERE LOWER(PRO.name) LIKE :LIKENAME OR LOWER(PRO.code) LIKE :LIKENAME"; // search by name --> PRO.name, search by code --> PRO.code
		}
		hql += " ORDER BY PRO.createDate DESC ";

//		instead of 
//		Query<Product> query = session.createQuery(hql);
//		List<Product> products = query.getResultList();
//		List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
//		for (Product product : products) {
//			ProductInfo productInfo = new ProductInfo();
//			productInfo.setCode(product.getCode());
//			productInfo.setName(product.getName());
//			productInfo.setPrice(product.getPrice());
//			productInfos.add(productInfo);
//		}

		Query<ProductInfo> query = session.createQuery(hql);
		if (likeName != null && likeName.length() > 0) {
			query.setParameter("LIKENAME", "%" + likeName.toLowerCase() + "%");
//			PaginationResult<ProductInfo> paginationResult = new PaginationResult(query, page, maxResult, maxNavigationPage);
//			return paginationResult;
		}
		return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);

	}

	@Override
	public Product getProductByCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT PRO FROM Product PRO WHERE PRO.code = :CODE";
		Query<Product> query = session.createQuery(hql);
		query.setParameter("CODE", code);
		Product product = (Product) query.uniqueResult();
		return product;
	}

	@Override
	public ProductInfo getProductInfoByCode(String code) {
		Product product = getProductByCode(code);
		if (product == null) {
			return null;
		}
		ProductInfo productInfo = new ProductInfo(product.getCode(), product.getName(), product.getPrice());
		return productInfo;
	}

	@Override
	public void saveProductInfo(ProductInfo productInfo) {
		Session session = sessionFactory.getCurrentSession();
		String code = productInfo.getCode();
		Product product = null;
		boolean isNew = false;

		if (code != null) {
			product = getProductByCode(code);
		}
		if (product == null) {
			isNew = true;
			product = new Product();
			product.setCreateDate(new Date());
		}
		product.setCode(code);
		product.setName(productInfo.getName());
		product.setPrice(productInfo.getPrice());

		if (productInfo.getFileData() != null) {
			byte[] image = productInfo.getFileData().getBytes();
			if (image != null && image.length > 0) {
				product.setImage(image);
			}
		}
		if (isNew) {
			session.persist(product);
		}
		// If fail in database, it will throw out exception immedately
		session.flush();
	}

	@Override
	public boolean removeProductByCode(String code) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "DELETE FROM Product PRO WHERE PRO.code = :code1";
			Query query = session.createQuery(hql);
			query.setParameter("code1", code);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	

}
