package com.farzaneh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class errorServlet
 */
@WebServlet("/errorServlet")
public class errorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public errorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		request.setAttribute("exception", exception);
		request.setAttribute("statusCode", statusCode);
		request.setAttribute("servletName", servletName);
		request.getRequestDispatcher("/jsps/errorPage.jsp").forward(request, response);

//		response.setContentType("text/html");
//		PrintWriter writer = response.getWriter();
//		writer.write("<html><header> error acurred </heder><bode>");
//		writer.write("<ul><li>Exception Type:" + exception + "</li>");
//		writer.write("<ul><li>Status Code:" + statusCode + "</li>");
//		writer.write("<ul><li>Servlet Name:" + servletName + "</li>");
//		writer.write("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
