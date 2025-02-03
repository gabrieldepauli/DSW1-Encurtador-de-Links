package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageShortenLinkCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("loggedin", getLoggedUser(request) != null);
		return "/encurtar-link.jsp";
	}
	
	private User getLoggedUser(HttpServletRequest request) {
		var session = request.getSession(false);
		
		if (session != null) {
			var user = (User) session.getAttribute("user_id");
			return user;
		}
		
		return null;
	}
}
