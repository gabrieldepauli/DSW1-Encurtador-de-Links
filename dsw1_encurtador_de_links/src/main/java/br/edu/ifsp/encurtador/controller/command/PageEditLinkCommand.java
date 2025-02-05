package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import java.util.Objects;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageEditLinkCommand extends CommandAuthenticator implements Command {
	private final LinkDao linkDao;
	
	public PageEditLinkCommand() {
		this.linkDao = new LinkDaoFactory().getInstance(DaoImplementation.MYSQL);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			super.checkSession(request);
			String idStr = request.getParameter("id");
			
			if (StringUtils.isNotBlank(idStr)) {
				Integer id = Integer.parseInt(idStr);
				User user = getLoggedUser(request);
				Link link = linkDao.getByID(id);
				
				if (Objects.equals(link.getEmailCreator(), user.getEmail())) {				
					request.setAttribute("link", link);
				}
			}
			
			return "/loggedin/personalizar-link.jsp";			
		}
		catch(IllegalAccessException e) {
			System.err.println("Acesso negado");
			return "errors/404.jsp";
		}
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
