package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveLinkCommand implements Command {
	
	private static final String BASE_URL = "localhost:8080/encurtado.com/";
	private final LinkDao linkDao;
	
	public SaveLinkCommand() {
		this.linkDao = new LinkDaoFactory().getInstance(DaoImplementation.MYSQL);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		
		String urlOriginal = request.getParameter("link");
		String identifier = request.getParameter("identifier");
		boolean privateLink = request.getParameter("privateLink") != null;
		User user = getLoggedUser(request);
		
		if (StringUtils.isBlank(identifier)) {
			identifier = generateCode();
		}
		
		String originPage = request.getParameter("origin");
		boolean success = false;
		
		if (StringUtils.isBlank(idStr)) {
			Link link = new Link();
			link.setUrlOriginal(urlOriginal);
			link.setUrlEncurtada(identifier);
			link.setPrivateLink(privateLink);
			link.setEmailCreator(user != null ? user.getEmail() : null);
			success = linkDao.create(link);
		}
		else {
			Integer id = Integer.parseInt(idStr);
			Link link = linkDao.getByID(id);
			link.setUrlOriginal(urlOriginal);
			link.setUrlEncurtada(identifier);
			link.setPrivateLink(privateLink);
			link.setEmailCreator(user.getEmail());
			success = linkDao.update(link);
		}
		
		if (success) {
			request.setAttribute("successMessage", "Deu certo! Link encurtado: " + BASE_URL + identifier);
		} else {
		    request.setAttribute("errorMessage", "Erro ao encurtar o link. Por favor, tente novamente.");
		}
		
		request.setAttribute("loggedin", getLoggedUser(request) != null);
		
		return "/encurtar-link.jsp";
	}
	
	private User getLoggedUser(HttpServletRequest request) {
		var session = request.getSession(false);
		
		if (session != null) {
			var user = (User) session.getAttribute("user_id");
			return user;
		}
		
		return null;
	}

    private String generateCode() {	
    	Random random = new Random();
        int tamanho = 5 + random.nextInt(8);

        String code = UUID.randomUUID().toString().replace("-", "");
        
        return code.substring(0, tamanho);
    }
}
