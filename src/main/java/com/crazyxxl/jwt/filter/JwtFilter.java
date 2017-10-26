package com.crazyxxl.jwt.filter;

import com.crazyxxl.jwt.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter implements Filter {

	private String authFail;
	private String startTag;
	private String claims = "claims";


	public JwtFilter(String authFail, String startTag, String claims) {
		this.authFail = authFail;
		this.startTag = startTag;
		this.claims = claims;
	}

	public JwtFilter() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		String requestMethod = request.getMethod();
		if (!requestMethod.equals("OPTIONS")) {
			String authHeader = request.getHeader("Authorization");
			String token = "";
			if(this.startTag == null){
				if (authHeader == null) {
					request.getRequestDispatcher(this.authFail).forward(request, (HttpServletResponse) res);
					return;
				}
				token = authHeader;
			}else {
				if (authHeader == null || !authHeader.startsWith(this.startTag + " ")) {
					request.getRequestDispatcher(this.authFail).forward(request, (HttpServletResponse) res);
					return;
				}
				token = authHeader.substring(this.startTag.length() + 1);
			}
			try {
				Claims claims = JwtUtils.parseJWT(token);
				request.setAttribute(this.claims, claims);
			} catch (Exception e) {
				request.getRequestDispatcher(this.authFail).forward(request, (HttpServletResponse) res);
				return;
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}

}
