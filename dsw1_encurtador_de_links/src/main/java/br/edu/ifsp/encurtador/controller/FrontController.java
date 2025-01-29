package br.edu.ifsp.encurtador.controller;

import java.io.IOException;

import br.edu.ifsp.encurtador.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/front.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String BASE_PACKAGE = "br.edu.ifsp.encurtador.controller.command.";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	@SuppressWarnings("deprecation")
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String commandStr = request.getParameter("command");
			
			Command command = (Command) Class.forName(BASE_PACKAGE + commandStr).newInstance();
			
			String page = command.execute(request, response);
			
			request.getRequestDispatcher(page).forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
