package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageHomeCommand extends CommandAuthenticator implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			super.checkSession(request);
			return "/loggedin/logged.jsp";			
		}
		catch(IllegalAccessException e) {
			System.err.println("Acesso negado");
			return "errors/404.jsp";
		}
	}
}
