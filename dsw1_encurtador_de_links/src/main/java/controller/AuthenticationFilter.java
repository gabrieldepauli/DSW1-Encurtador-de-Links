package controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/logged.do", "/loggedin/*" } )
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		if (session != null && session.getAttribute("user_id") != null) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("message", "Acesso permitido apenas para usuário logado.");
			
			var dispatcher = request.getRequestDispatcher("front.do?action=loginForm");
			dispatcher.forward(request, response);
		}	
		
	}
	
}
