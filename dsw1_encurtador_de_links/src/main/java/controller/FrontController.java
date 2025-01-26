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
import controller.command.FrontCommandFactory;
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
		String action = request.getParameter("action");
		
		String view = new FrontCommandFactory().getCommand(action, request, response).execute(request, response);
		if (view != null) {
	        var dispatcher = request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);
	    }
		
	}

}
