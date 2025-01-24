package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controller.command.Command;
import controller.command.ErrorCommand;
import controller.command.ErrorPageCommand;
import controller.command.RedirecionarLinkCommand;
import controller.command.FrontCommands.CadastroCommand;
import controller.command.FrontCommands.FormCadastroCommand;
import controller.command.FrontCommands.FormLoginCommand;
import controller.command.FrontCommands.LoginCommand;

@WebServlet("/front.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = null;
		String action = request.getParameter("action");
		
		if("loginForm".equals(action)) { 
			command = new FormLoginCommand();
		} else if("getLogin".equals(action)) {
			command = new LoginCommand();
		} else if("cadastroForm".equals(action)) {
			command = new FormCadastroCommand();
		} else if("cadastro".equals(action)) {
			command = new CadastroCommand();
		} else if("getLogin".equals(action)) {
			command = new LoginCommand();
		} else if("redirecionarLink".equals(action)) { 
			command = new RedirecionarLinkCommand();
		} else if("errorPage".equals(action)) { 
			command = new ErrorPageCommand();
		} else {
			command = new ErrorCommand();
		}
		
		String view = command.execute(request, response);
		if (view != null) {
	        var dispatcher = request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);
	    }
		
	}

}
