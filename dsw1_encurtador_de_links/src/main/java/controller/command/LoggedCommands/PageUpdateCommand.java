package controller.command.LoggedCommands;

import java.io.IOException;

import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageUpdateCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int idLink = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("id", idLink);
		return "/loggedin/updateLink.jsp";
	}

}
