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

@WebServlet("/shoppingCartServlet")
public class shoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public shoppingCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String randomId = null;
		float totalCost = 0;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("user")) {
				randomId = cookie.getValue();
			}
		}
		List<Product> productList = new ArrayList<>();
		List<Product> productListCart = new ArrayList<>();
		List<Product> productListAll = new ArrayList<>();
		ProductDAO productDAO = new ProductDAO();
		String actionValue = request.getParameter("action");
		String productId = request.getParameter("ProductId");
		if (!(actionValue == null || actionValue.trim().equals(""))) {
			try {
				if (actionValue.equals("add")) {
					if (!(productId == null || productId.trim().equals(""))) {
						if (productId.matches("\\d+")) {
							Integer ProductIdInt = Integer.parseInt(productId);
							ProductDAO productDao = new ProductDAO();

							Product selectedProduct = productDao.searchById(ProductIdInt);
							if (ProductDAO.PERSON_CART_MAP.containsKey(randomId)) {
								productList = ProductDAO.PERSON_CART_MAP.get(randomId);
								productList.add(selectedProduct);
								ProductDAO.PERSON_CART_MAP.put(randomId, productList);
							} else {
								productList.add(selectedProduct);
								ProductDAO.PERSON_CART_MAP.put(randomId, productList);
							}
							productListAll = productDAO.showAllProduct();
							request.setAttribute("productList", productListAll);
							List<ProductCategory> categoryList = productDAO.showAllCategory();
							request.setAttribute("categoryList", categoryList);
							request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);

						}
					}
				}
				if (actionValue.equals("cart")) {
					productListCart = ProductDAO.PERSON_CART_MAP.get(randomId);
					if (productListCart != null) {
						for (Product productItem : productListCart) {
							totalCost = productItem.getCost() + totalCost;
						}
					}
					request.setAttribute("productListCart", productListCart);
					request.setAttribute("totalCost", totalCost);
					request.getRequestDispatcher("/jsps/secured/shoppingCart.jsp").forward(request, response);
				}
				if (actionValue.equals("back")) {
					productListAll = productDAO.showAllProduct();
					request.setAttribute("productList", productListAll);
					List<ProductCategory> categoryList = productDAO.showAllCategory();
					request.setAttribute("categoryList", categoryList);
					request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);
				}
				if (actionValue.equals("delete")) {
					if (!(productId == null || productId.trim().equals(""))) {
						if (productId.matches("\\d+")) {
							Integer ProductIdInt = Integer.parseInt(productId);
							ProductDAO productDao = new ProductDAO();

							Product selectedProduct = productDao.searchById(ProductIdInt);
							if (ProductDAO.PERSON_CART_MAP.containsKey(randomId)) {
								productList = ProductDAO.PERSON_CART_MAP.get(randomId);
								for (Product products : productList) {
									if (products.equals(selectedProduct)) {
										productList.remove(products);
									}
								}
								ProductDAO.PERSON_CART_MAP.put(randomId, productList);
							} else {
								throw new ProcessException("error during remove products");
							}
							if (productList != null) {
								for (Product productItem : productList) {
									totalCost = productItem.getCost() + totalCost;
								}
							}
							request.setAttribute("totalCost", totalCost);
							productListCart = ProductDAO.PERSON_CART_MAP.get(randomId);
							request.setAttribute("productListCart", productListCart);
							request.getRequestDispatcher("/jsps/secured/shoppingCart.jsp").forward(request, response);

						}
					}
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
		doGet(request, response);
	}

}
