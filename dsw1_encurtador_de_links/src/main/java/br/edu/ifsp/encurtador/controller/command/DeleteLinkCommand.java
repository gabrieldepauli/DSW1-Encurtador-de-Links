package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteLinkCommand implements Command {
	
	private final LinkDao linkDao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		Integer id = StringUtils.isNotBlank(idString) ? Integer.parseInt(idString) : null;
		
		if (id != null) {
			var user = getLoggedUser(request);
			
			if (user != null) {
				var link = linkDao.getByID(user, id);
				boolean deletionSuccess = linkDao.delete(user, link);
				
				if (deletionSuccess) {
					request.setAttribute("successMessage", "Link deletado com sucesso!");
				}
				else {
					request.setAttribute("errorMessage", "Houve um problema ao deletar o link. Por favor, tente novamente.");
				}
			}
		}
		
		 response.sendRedirect(request.getContextPath() + "/front.do?command=GetLinksCommand");
		 return null;
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
