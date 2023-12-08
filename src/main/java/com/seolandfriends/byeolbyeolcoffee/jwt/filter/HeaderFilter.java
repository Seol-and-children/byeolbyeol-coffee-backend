package com.seolandfriends.byeolbyeolcoffee.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class HeaderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		// HttpServletResponse res = (HttpServletResponse) response;
		//
		// chain.doFilter(request, res);

		HttpServletResponse res = (HttpServletResponse)response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers",
					"Access-Control-Allow-Origin, Access-Control-Allow-Headers, X-Requested-With, Content-Type, Authorization, X-XSRF-token");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(request, res);
	}
}