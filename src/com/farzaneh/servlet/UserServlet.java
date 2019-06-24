package com.farzaneh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farzaneh.exception.ProcessException;
import com.farzaneh.model.Person;
import com.farzaneh.model.Product;
import com.farzaneh.model.ProductCategory;
import com.farzaneh.service.dao.PersonDAO;
import com.farzaneh.service.dao.ProductDAO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Person person = new Person();
		ProductDAO productDAO = new ProductDAO();
		Cookie personCookie;
		String actionMode = request.getParameter("action-mode");
		if (!(actionMode == null || actionMode.trim().equals(""))) {
			try {
				if (actionMode.equals("Login")) {
					person.setEmailAddress(request.getParameter("email"));
					person.setPrincipalName(request.getParameter("user-name"));
					PersonDAO personDao = new PersonDAO();
					Person member = personDao.findPerson(person);
					request.setAttribute("member", member);
					String randomId = UUID.randomUUID().toString();
					PersonDAO.PERSON_MAP.put(randomId, member);
					personCookie = new Cookie("user", randomId);
					response.addCookie(personCookie);
					List<Product> productList = productDAO.showAllProduct();
					request.setAttribute("productList", productList);
					List<ProductCategory> categoryList = productDAO.showAllCategory();
					request.setAttribute("categoryList", categoryList);
					request.getRequestDispatcher("/jsps/products.jsp").forward(request, response);
				}
				if (actionMode.equals("save")) {
					person.setFirstName(request.getParameter("first-name"));
					person.setLastName(request.getParameter("last-name"));
					person.setEmailAddress(request.getParameter("email-address"));
					person.setPrincipalName(request.getParameter("user-name"));
					person.setPersonTypeCode("CUST");
					PersonDAO personDAO = new com.farzaneh.service.dao.PersonDAO();
					personDAO.insertUser(person);
					request.setAttribute("newPerson", person);
					request.getRequestDispatcher("/jsps/home.jsp").forward(request, response);

				}
			} catch (SQLException e) {
				throw new ProcessException("error accured in process about person", e);
			}

		}

	}
}
