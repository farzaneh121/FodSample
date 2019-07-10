package com.farzaneh.service.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	private static final String FIND_BY_CATEGORY_ID = "select  p.product_id, p.product_name , t.category_name , p.cost_price , \n"
			+ "p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1\n"
			+ "and t.category_id=?";
	private static final String FIND_BY_NAME = "select  p.product_id, p.product_name , t.category_name , p.cost_price , \n"
			+ "p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1\n"
			+ "and lower(p.product_name) =?";
	private static final String FIND_BY_ID = "select  p.product_id, p.product_name , t.category_name , p.cost_price , \n"
			+ "p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1\n"
			+ "and p.product_id =?";
	private static final String FIND_BY_NAME_CATEGORY = "select  p.product_id, p.product_name , t.category_name , p.cost_price , \n"
			+ "p.product_status from fod.products_base p, fod.product_categories_base c , fod.category_translations t\n"
			+ "where p.category_id=c.category_id and t.category_id=c.category_id and t.object_version_id=1\n"
			+ "and lower(p.product_name) =? and t.category_id=?";
	public static final Map<String, List<Product>> PERSON_CART_MAP = new ConcurrentHashMap<>();

	public ProductDAO() {
		super();
	}

	public List<Product> showAllProduct() {
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

		} catch (SQLException e) {
			throw new ProcessException("an error occurred during show all products!", e);
		} finally {
			dbConneection.closeConnection();
		}
	}

	public List<ProductCategory> showAllCategory() {
		DbConneection dbConneection = new DbConneection();
		List<ProductCategory> categoryList = new ArrayList<>();
		try (Statement statement = dbConneection.getConnectionInstance().createStatement();
				ResultSet result = statement.executeQuery(SHOW_CATEGORY);) {

			while (result.next()) {
				ProductCategory category = new ProductCategory();
				category.setId(result.getInt(1));
				category.setName(result.getString(2));
				categoryList.add(category);
			}
			return categoryList;
		} catch (SQLException e) {
			throw new ProcessException("an error occurred during show all categories!", e);
		} finally {
			dbConneection.closeConnection();
		}
	}

	public List<Product> searchByName(String productName) {
		DbConneection dbConneection = new DbConneection();
		List<Product> productsList = new ArrayList<>();

		try (PreparedStatement statement = dbConneection.getConnectionInstance().prepareStatement(FIND_BY_NAME)) {
			statement.setString(1, productName.toLowerCase());
			try (ResultSet result = statement.executeQuery();) {
				while (result.next()) {
					Product product = new Product();
					product.setId(result.getInt(1));
					product.setName(result.getString(2));
					product.setCategory(new ProductCategory(result.getString(3)));
					product.setCost(result.getFloat(4));
					product.setProductStatus(result.getString(5));
					productsList.add(product);
				}
			}
			return productsList;

		} catch (SQLException e) {
			throw new ProcessException("an error occurred during search the product!", e);
		} finally {
			dbConneection.closeConnection();
		}
	}

	public Product searchById(Integer productID) {
		DbConneection dbConneection = new DbConneection();
		Product product = new Product();
		try (PreparedStatement statement = dbConneection.getConnectionInstance().prepareStatement(FIND_BY_ID)) {
			statement.setInt(1, productID);
			try (ResultSet result = statement.executeQuery();) {
				if (result.next()) {
					product.setId(result.getInt(1));
					product.setName(result.getString(2));
					product.setCategory(new ProductCategory(result.getString(3)));
					product.setCost(result.getFloat(4));
					product.setProductStatus(result.getString(5));
				} else {
					throw new ProcessException("we dont have this ID");
				}
			}

			return product;

		} catch (SQLException e) {
			throw new ProcessException("an error occurred during search the product by Id!", e);
		} finally {
			dbConneection.closeConnection();
		}
	}

	public List<Product> searchByCategoryId(Integer categoryId) {
		DbConneection dbConneection = new DbConneection();
		List<Product> productsList = new ArrayList<>();

		try (PreparedStatement statement = dbConneection.getConnectionInstance()
				.prepareStatement(FIND_BY_CATEGORY_ID)) {
			statement.setInt(1, categoryId);
			try (ResultSet result = statement.executeQuery();) {
				while (result.next()) {
					Product product = new Product();
					product.setId(result.getInt(1));
					product.setName(result.getString(2));
					product.setCategory(new ProductCategory(result.getString(3)));
					product.setCost(result.getFloat(4));
					product.setProductStatus(result.getString(5));
					productsList.add(product);
				}
			}
			return productsList;

		} catch (SQLException e) {
			throw new ProcessException("an error occurred during search the category by Id!", e);
		} finally {
			dbConneection.closeConnection();
		}
	}

	public List<Product> searchByNameAndCategoryId(String productName, Integer categoryId) {
		DbConneection dbConneection = new DbConneection();
		List<Product> productsList = new ArrayList<>();

		try (PreparedStatement statement = dbConneection.getConnectionInstance()
				.prepareStatement(FIND_BY_NAME_CATEGORY)) {
			statement.setString(1, productName.toLowerCase());
			statement.setInt(2, categoryId);
			try (ResultSet result = statement.executeQuery();) {
				while (result.next()) {
					Product product = new Product();
					product.setId(result.getInt(1));
					product.setName(result.getString(2));
					product.setCategory(new ProductCategory(result.getString(3)));
					product.setCost(result.getFloat(4));
					product.setProductStatus(result.getString(5));
					productsList.add(product);
				}
			}
			return productsList;

		} catch (SQLException e) {
			throw new ProcessException("an error occurred during search categories and products!", e);
		} finally {
			dbConneection.closeConnection();
		}
	}

}
