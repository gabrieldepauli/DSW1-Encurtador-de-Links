package controller.command.LoggedCommands;

import java.io.IOException;
import java.util.List;

import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LinkDaoFactory;
import model.entity.Link;
import model.entity.User;
import model.enums.DaoImplementation;

public class ListarLinksCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var dao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);
		
		var sessao = request.getSession(false);
		
		User user = (User) sessao.getAttribute("user_id");
		
		List<Link> links = dao.getAllLinks(user);
		
		if(links.isEmpty()) {
			request.setAttribute("message", "Sua lista de links está vazia!");
			request.setAttribute("messageType", "error");
		}	
		
		request.setAttribute("lista_links", links);
		return "logged.do?action=pageMeusLinks";
	}

}
