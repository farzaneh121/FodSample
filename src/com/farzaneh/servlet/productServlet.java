package com.farzaneh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farzaneh.exception.ProcessException;
import com.farzaneh.model.Product;
import com.farzaneh.model.ProductCategory;
import com.farzaneh.service.dao.PersonDAO;
import com.farzaneh.service.dao.ProductDAO;

@WebServlet("/productServlet")
public class productServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public productServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String randomId = null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("user")) {
				randomId = cookie.getValue();
			}
		}

		List<Product> productListAll = new ArrayList<>();
		ProductDAO productDAO = new ProductDAO();
		String actionValue = request.getParameter("action");
		if (!(actionValue == null || actionValue.trim().equals(""))) {
			try {
				if (actionValue.equals("show")) {
					productListAll = productDAO.showAllProduct();
					request.setAttribute("productList", productListAll);
					List<ProductCategory> categoryList = productDAO.showAllCategory();
					request.setAttribute("categoryList", categoryList);
					request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);
				}
				if (actionValue.equals("logout")) {
					PersonDAO.PERSON_MAP.remove(randomId);
					request.getRequestDispatcher("/jsps/home.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				throw new ProcessException("error during find products", e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		try {
			String productName = request.getParameter("product-name");
			Integer categoryId = Integer.parseInt(request.getParameter("product-category"));

			if ((productName == null || productName.trim().equals("")) && (categoryId == null)) {
				List<Product> productList = productDAO.showAllProduct();
				request.setAttribute("productList", productList);

			} else if (!(productName == null || productName.trim().equals(""))) {
				if (categoryId == null) {
					List<Product> productList = productDAO.searchByName(productName);
					request.setAttribute("productList", productList);
				} else {
					List<Product> productList = productDAO.searchByNameAndCategoryId(productName, categoryId);
					request.setAttribute("productList", productList);
				}
			} else if (categoryId != null) {
				List<Product> productList = productDAO.searchByCategoryId(categoryId);
				request.setAttribute("productList", productList);
			}
			List<ProductCategory> categoryList = productDAO.showAllCategory();
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ProcessException("error during search", e);
		}
	}

}
