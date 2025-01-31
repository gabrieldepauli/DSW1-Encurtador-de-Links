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

public class CreateLinkCommand implements Command {
	
	private static final String BASE_URI = "localhost:8080";
	private static final String BASE_URL = "/encurtado.com/";
	private final LinkDao linkDao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlOriginal = request.getParameter("link");
		String identifier = request.getParameter("identifier");
		
		String urlEncurtada = getShortenedLink(identifier);
		
		Link link = new Link(urlOriginal, urlEncurtada);
		
		User usuario = getLoggedUser(request);
		
		boolean sucess = linkDao.create(usuario, link);
	    
		 if (sucess) {
		     request.setAttribute("successMessage", "Deu certo! Link encurtado: " + BASE_URI + urlEncurtada);
		 } else {
		     request.setAttribute("errorMessage", "Erro ao encurtar o link. Por favor, tente novamente.");
		 }
		
		return  StringUtils.isNotBlank(identifier) ? 
				"/loggedin/personalizar-link.jsp" : "/loggedin/encurtar-link.jsp";
	}
	
	private User getLoggedUser(HttpServletRequest request) {
		var session = request.getSession(false);
		
		if (session != null) {
			var user = (User) session.getAttribute("user_id");
			return user;
		}
		
		return null;
	}
	
	private String getShortenedLink(String identifier) {
		
		if (identifier != null) {
			return BASE_URL + identifier;
		}
		
		return BASE_URL + generateCode();
	}

    private String generateCode() {	
    	Random random = new Random();
        int tamanho = 5 + random.nextInt(8);

        String code = UUID.randomUUID().toString().replace("-", "");
        
        return code.substring(0, tamanho);
    }
}
