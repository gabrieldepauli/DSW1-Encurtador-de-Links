package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetLinksCommand implements Command {
	
	private final LinkDao linkDao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var sessao = request.getSession(false);
		
		if (sessao != null) {
			User user = (User) sessao.getAttribute("user_id");	
			List<Link> links = linkDao.getAllLinks(user);
			request.setAttribute("links", links);
		}
		
		return "/loggedin/meus-links.jsp";
	}
}
