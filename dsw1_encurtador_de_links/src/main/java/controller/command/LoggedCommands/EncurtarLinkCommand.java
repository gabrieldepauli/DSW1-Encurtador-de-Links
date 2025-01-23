package controller.command.LoggedCommands;

import java.io.IOException;
import java.util.Random;

import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EncurtarLinkCommand implements Command{
	
	
	
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return "logged.do?action=pageEncurtador";
	}

}
