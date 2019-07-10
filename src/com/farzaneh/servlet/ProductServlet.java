package com.farzaneh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farzaneh.model.Product;
import com.farzaneh.model.ProductCategory;
import com.farzaneh.service.dao.ProductDAO;

@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PRODUCT_LIST_KEY = "productList";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductDAO productDAO = new ProductDAO();
		String actionValue = request.getParameter("action");
		if (!(actionValue == null || actionValue.trim().equals(""))) {

			if (actionValue.equals("show")) {
				List<Product> productListAll = productDAO.showAllProduct();
				request.setAttribute(PRODUCT_LIST_KEY, productListAll);
				List<ProductCategory> categoryList = productDAO.showAllCategory();
				request.setAttribute("categoryList", categoryList);
				request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);
			}
			if (actionValue.equals("logout")) {
				request.getSession().removeAttribute("user");
				request.getRequestDispatcher("/jsps/home.jsp").forward(request, response);
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		Integer categoryId = null;
		String productName = request.getParameter("product-name");
		String productCategory = request.getParameter("product-category");
		if (!(productCategory == null || productCategory.trim().equals(""))) {
			categoryId = Integer.parseInt(productCategory);
		}
		if ((productName == null || productName.trim().equals("")) && (categoryId == null)) {
			List<Product> productList = productDAO.showAllProduct();
			request.setAttribute(PRODUCT_LIST_KEY, productList);

		} else if (!(productName == null || productName.trim().equals(""))) {
			if (categoryId == null) {
				List<Product> productList = productDAO.searchByName(productName);
				request.setAttribute(PRODUCT_LIST_KEY, productList);
			} else {
				List<Product> productList = productDAO.searchByNameAndCategoryId(productName, categoryId);
				request.setAttribute(PRODUCT_LIST_KEY, productList);
			}
		} else if (categoryId != null) {
			List<Product> productList = productDAO.searchByCategoryId(categoryId);
			request.setAttribute(PRODUCT_LIST_KEY, productList);
		}
		List<ProductCategory> categoryList = productDAO.showAllCategory();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);

	}

}
