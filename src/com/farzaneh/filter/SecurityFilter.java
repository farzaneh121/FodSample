package com.farzaneh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.farzaneh.service.dao.PersonDAO;

@WebFilter(filterName = "/SecurityFilter", urlPatterns = "/shoppingCartServlet")
public class SecurityFilter implements Filter {

	public SecurityFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session= req.getSession();
		if(session!=null && session.getAttribute("user")!=null) {
					chain.doFilter(request, response);
				} else {
					request.setAttribute("exception", "FORBIDDEN REQUEST");
					request.setAttribute("statusCode", 403);
					request.setAttribute("servletName", "you must be login for use of the Shopping cart");
					request.getRequestDispatcher("/jsps/errorPage.jsp").forward(request, response);
				}
			}
	

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
