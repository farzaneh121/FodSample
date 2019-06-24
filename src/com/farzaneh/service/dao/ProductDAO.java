package com.farzaneh.service.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farzaneh.exception.ProcessException;
import com.farzaneh.model.Product;
import com.farzaneh.model.ProductCategory;
import com.farzaneh.service.DbConneection;

public class ProductDAO {

	private static final String SHOW_PRODUCT = "select p.product_id, p.product_name , t.category_name , p.cost_price ,\n"
			+ "			p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "			where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1";
	private static final String SHOW_CATEGORY = "select t.category_id, t.category_name from  fod.category_translations t , fod.product_categories_base c\n"
			+ "where t.object_version_id=1 and t.category_id=c.category_id and c.parent_category_id is not null \n"
			+ "order by t.category_name";
	private static final String FIND_by_CATEGORY_id = "select  p.product_id, p.product_name , t.category_name , p.cost_price , \n"
			+ "p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1\n"
			+ "and t.category_id=?";
	private static final String FIND_by_NAME = "select  p.product_id, p.product_name , t.category_name , p.cost_price , \n"
			+ "p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1\n"
			+ "and lower(p.product_name) =?";
	private static final String FIND_by_ID = "select  p.product_id, p.product_name , t.category_name , p.cost_price , \n"
			+ "p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1\n"
			+ "and p.product_id =?";
	private static final String FIND_by_NAME_CATEGORY = "select  p.product_id, p.product_name , t.category_name , p.cost_price , \n"
			+ "p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1\n"
			+ "and lower(p.product_name) =? and t.category_id=?";
	public static final Map<String, List<Product>> PERSON_CART_MAP = new HashMap<String, List<Product>>();

	public ProductDAO() {
		super();
	}

	public List<Product> showAllProduct() throws SQLException {
		DbConneection dbConneection = new DbConneection();
		List<Product> productsList = new ArrayList<>();
		try (Statement statement = dbConneection.getConnectionInstance().createStatement()) {
			ResultSet result = statement.executeQuery(SHOW_PRODUCT);
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));
				product.setCategory(new ProductCategory(result.getString(3)));
				product.setCost(result.getFloat(4));
				product.setProductStatus(result.getString(5));
				productsList.add(product);
			}
			return productsList;

		} finally {
			dbConneection.closeConnection();
		}
	}

	public List<ProductCategory> showAllCategory() throws SQLException {
		DbConneection dbConneection = new DbConneection();
		List<ProductCategory> categoryList = new ArrayList<>();
		try (Statement statement = dbConneection.getConnectionInstance().createStatement()) {
			ResultSet result = statement.executeQuery(SHOW_CATEGORY);
			while (result.next()) {
				ProductCategory category = new ProductCategory();
				category.setId(result.getInt(1));
				category.setName(result.getString(2));
				categoryList.add(category);
			}
			return categoryList;
		} finally {
			dbConneection.closeConnection();
		}
	}

	public List<Product> searchByName(String productName) throws SQLException {
		DbConneection dbConneection = new DbConneection();
		List<Product> productsList = new ArrayList<>();

		try (PreparedStatement statement = dbConneection.getConnectionInstance().prepareStatement(FIND_by_NAME)) {
			statement.setString(1, productName.toLowerCase());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));
				product.setCategory(new ProductCategory(result.getString(3)));
				product.setCost(result.getFloat(4));
				product.setProductStatus(result.getString(5));
				productsList.add(product);
			}
			return productsList;

		} finally {
			dbConneection.closeConnection();
		}
	}

	public Product searchById(Integer productID) throws SQLException {
		DbConneection dbConneection = new DbConneection();
		Product product = new Product();
		try (PreparedStatement statement = dbConneection.getConnectionInstance().prepareStatement(FIND_by_ID)) {
			statement.setInt(1, productID);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				product.setId(result.getInt(1));
				product.setName(result.getString(2));
				product.setCategory(new ProductCategory(result.getString(3)));
				product.setCost(result.getFloat(4));
				product.setProductStatus(result.getString(5));
			} else {
				throw new ProcessException("we dont have this ID");
			}

			return product;

		} finally {
			dbConneection.closeConnection();
		}
	}

	public List<Product> searchByCategoryId(Integer categoryId) throws SQLException {
		DbConneection dbConneection = new DbConneection();
		List<Product> productsList = new ArrayList<>();

		try (PreparedStatement statement = dbConneection.getConnectionInstance()
				.prepareStatement(FIND_by_CATEGORY_id)) {
			statement.setInt(1, categoryId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));
				product.setCategory(new ProductCategory(result.getString(3)));
				product.setCost(result.getFloat(4));
				product.setProductStatus(result.getString(5));
				productsList.add(product);
			}
			return productsList;

		} finally {
			dbConneection.closeConnection();
		}
	}

	public List<Product> searchByNameAndCategoryId(String productName, Integer categoryId) throws SQLException {
		DbConneection dbConneection = new DbConneection();
		List<Product> productsList = new ArrayList<>();

		try (PreparedStatement statement = dbConneection.getConnectionInstance()
				.prepareStatement(FIND_by_NAME_CATEGORY)) {
			statement.setString(1, productName.toLowerCase());
			statement.setInt(2, categoryId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));
				product.setCategory(new ProductCategory(result.getString(3)));
				product.setCost(result.getFloat(4));
				product.setProductStatus(result.getString(5));
				productsList.add(product);
			}
			return productsList;

		} finally {
			dbConneection.closeConnection();
		}
	}

}
