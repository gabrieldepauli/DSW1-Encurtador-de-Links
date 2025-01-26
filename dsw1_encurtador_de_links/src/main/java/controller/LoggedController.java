package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controller.command.Command;
import controller.command.ErrorCommand;
import controller.command.LoggedCommandFactory;
import controller.command.RedirecionarLinkCommand;
import controller.command.LoggedCommands.DeleteCommand;
import controller.command.LoggedCommands.EncurtarLinkCommand;
import controller.command.LoggedCommands.ListarLinksCommand;
import controller.command.LoggedCommands.LogoutCommand;
import controller.command.LoggedCommands.PageEncurtadorCommand;
import controller.command.LoggedCommands.PageMeusLinksCommand;
import controller.command.LoggedCommands.PagePersonalizarCommand;
import controller.command.LoggedCommands.PersonalizarLinkCommand;
import controller.command.LoggedCommands.UserPageCommand;

@WebServlet("/logged.do")
public class LoggedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String view = new LoggedCommandFactory().getCommand(action, request, response).execute(request, response);
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

}
