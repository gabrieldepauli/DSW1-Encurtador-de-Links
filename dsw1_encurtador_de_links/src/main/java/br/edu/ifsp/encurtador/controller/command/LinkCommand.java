package br.edu.ifsp.encurtador.controller.command;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LinkCommand implements Command {
	
	private static final String BASE_URL = "/encurtado.com/";
	
	private final LinkDao linkDao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String action = request.getParameter("action");
	
		if ("shorten".equals(action)) {
			return encurtar(request);
		}
		else if ("getLinks".equals(action)) {
			return getUserLinks(request);
		}
		else if ("delete".equals(action)) {
			return deleteLink(request);
		}
	
		return null;
	}
	
	private String encurtar(HttpServletRequest request) {
		String urlOriginal = request.getParameter("link");
		String urlEncurtada = getShortenedLink();
		User usuario = (User) request.getSession().getAttribute("user_id");
		
		Link link = new Link(urlOriginal, urlEncurtada);;
		
		boolean sucess = linkDao.create(usuario, link);
	    
		 if (sucess) {
		     request.setAttribute("successMessage", "Deu certo! Link encurtado: " + urlEncurtada);
		 } else {
		     request.setAttribute("errorMessage", "Erro ao encurtar o link. Por favor, tente novamente.");
		 }
		
		return "/loggedin/encurtar-link.jsp";
	}
	
	private String getUserLinks(HttpServletRequest request) {
		var sessao = request.getSession(false);
		
		if (sessao != null) {
			User user = (User) sessao.getAttribute("user_id");	
			List<Link> links = linkDao.getAllLinks(user);
			request.setAttribute("links", links);
		}
		
		return "/loggedin/meus-links.jsp";
	}
	
	private String deleteLink(HttpServletRequest request) {
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
		
		return getUserLinks(request);
	}
	
	private User getLoggedUser(HttpServletRequest request) {
		var session = request.getSession(false);
		
		if (session != null) {
			var user = (User) session.getAttribute("user_id");
			return user;
		}
		
		return null;
	}
	
	public String getShortenedLink() {
		String identificador = generateCode();
		String url = BASE_URL + identificador;
		return url;
	}

    public String generateCode() {	
    	Random random = new Random();
        int tamanho = 5 + random.nextInt(8);

        String code = UUID.randomUUID().toString().replace("-", "");
        
        return code.substring(0, tamanho);
    }
}
