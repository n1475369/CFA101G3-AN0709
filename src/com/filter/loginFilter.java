package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class loginFilter implements Filter {

    
    public loginFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		if(uri.contains("memberBuyProfile.html")) {
			HttpSession session = req.getSession();
			Object user = session.getAttribute("user");
			if(user != null) {
				chain.doFilter(request, response);
			}else {
				res.sendRedirect(req.getContextPath()+"/front_end/member/login.html");
			}
		}else {
			chain.doFilter(request, response);
		}

		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
