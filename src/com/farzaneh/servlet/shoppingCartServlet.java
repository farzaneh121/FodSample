package com.farzaneh.servlet;

import java.io.IOException;
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
import com.farzaneh.service.dao.ProductDAO;

@WebServlet("/shoppingCartServlet")
public class shoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String actionValue = request.getParameter("action");
		if (!(actionValue == null || actionValue.trim().equals(""))) {

			if (actionValue.equals("add")) {
				addToCart(request, response);
			}
			if (actionValue.equals("cart")) {
				goToCart(request, response);
			}
			if (actionValue.equals("back")) {
				backToHome(request, response);
			}
			if (actionValue.equals("delete")) {
				deleteFromCart(request, response);
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
		doGet(request, response);
	}

	protected void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String randomId = null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("user")) {
				randomId = cookie.getValue();
			}
		}
		List<Product> productList = new ArrayList<>();
		ProductDAO productDAO = new ProductDAO();
		String productId = request.getParameter("ProductId");
		if ((!(productId == null || productId.trim().equals(""))) && (productId.matches("\\d+"))) {
			Integer productIdInt = Integer.parseInt(productId);
			ProductDAO productDao = new ProductDAO();

			Product selectedProduct = productDao.searchById(productIdInt);
			if (ProductDAO.PERSON_CART_MAP.containsKey(randomId)) {
				productList = ProductDAO.PERSON_CART_MAP.get(randomId);
				productList.add(selectedProduct);
				ProductDAO.PERSON_CART_MAP.put(randomId, productList);
			} else {
				productList.add(selectedProduct);
				ProductDAO.PERSON_CART_MAP.put(randomId, productList);
			}
			List<Product> productListAll = productDAO.showAllProduct();
			request.setAttribute("productList", productListAll);
			List<ProductCategory> categoryList = productDAO.showAllCategory();
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);

		}

	}

	protected void goToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String randomId = null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("user")) {
				randomId = cookie.getValue();
			}
		}
		float totalCost = 0;
		List<Product> productListCart = ProductDAO.PERSON_CART_MAP.get(randomId);
		if (productListCart != null) {
			for (Product productItem : productListCart) {
				totalCost = productItem.getCost() + totalCost;
			}
		}
		request.setAttribute("productListCart", productListCart);
		request.setAttribute("totalCost", totalCost);
		request.getRequestDispatcher("/jsps/secured/shoppingCart.jsp").forward(request, response);
	}

	protected void backToHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		List<Product> productListAll = productDAO.showAllProduct();
		request.setAttribute("productList", productListAll);
		List<ProductCategory> categoryList = productDAO.showAllCategory();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);
	}

	protected void deleteFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String randomId = null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("user")) {
				randomId = cookie.getValue();
			}
		}

		float totalCost = 0;
		String productId = request.getParameter("ProductId");
		if ((!(productId == null || productId.trim().equals(""))) && productId.matches("\\d+")) {

			Integer productIdInt = Integer.parseInt(productId);
			ProductDAO productDao = new ProductDAO();

			Product selectedProduct = productDao.searchById(productIdInt);
			if (ProductDAO.PERSON_CART_MAP.containsKey(randomId)) {
				ProductDAO.PERSON_CART_MAP.get(randomId).remove(selectedProduct);
			}
		} else {
			throw new ProcessException("error during remove products");
		}
		for (Product productItem : ProductDAO.PERSON_CART_MAP.get(randomId)) {
			totalCost = productItem.getCost() + totalCost;
		}
		request.setAttribute("totalCost", totalCost);
		List<Product> productListCart = ProductDAO.PERSON_CART_MAP.get(randomId);
		request.setAttribute("productListCart", productListCart);
		request.getRequestDispatcher("/jsps/secured/shoppingCart.jsp").forward(request, response);

	}

}
