package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.encurtador.model.dao.AcessoDao;
import br.edu.ifsp.encurtador.model.dao.AcessoDaoFactory;
import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Acesso;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageAccessesCommand extends CommandAuthenticator implements Command {
	
	private final AcessoDao acessoDao;
	private final LinkDao linkDao;
	
	public PageAccessesCommand() {
		this.acessoDao = new AcessoDaoFactory().getInstance(DaoImplementation.MYSQL);
		this.linkDao = new LinkDaoFactory().getInstance(DaoImplementation.MYSQL);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			super.checkSession(request);
			
			String idStr = request.getParameter("id");
			
			if (StringUtils.isNotBlank(idStr)) {
				Integer id = Integer.parseInt(idStr);
				Link link = linkDao.getByID(id);
				List<Acesso> accesses = acessoDao.getAllAcessos(link);
				request.setAttribute("accesses", accesses);				
			}
			
			return "/loggedin/acessos.jsp";
		}
		catch(IllegalAccessException e) {
			System.err.println("Acesso negado");
			return "/errors/404.jsp";
		}
	}
}
