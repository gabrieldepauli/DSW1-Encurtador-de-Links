package br.edu.ifsp.encurtador.controller.command;

import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

abstract class CommandAuthenticator {
	protected void checkSession(HttpServletRequest request) throws IllegalAccessException {
		var user = getLoggedUser(request);
		if (user == null) throw new IllegalAccessException("Illegal access");
	}
	
	private User getLoggedUser(HttpServletRequest request) {
		var session = request.getSession(false);
		
		if (session != null) {
			User user = (User) session.getAttribute("user_id");	
			return user;
		}
		
		return null;
	}
}
