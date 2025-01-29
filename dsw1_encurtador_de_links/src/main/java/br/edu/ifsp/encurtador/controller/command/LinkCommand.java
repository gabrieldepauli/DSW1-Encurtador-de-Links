package br.edu.ifsp.encurtador.controller.command;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;

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
			
			if (CollectionUtils.isNotEmpty(links)) {
				request.setAttribute("errorMessage", "Sua lista de links está vazia!");
			}
			
			request.setAttribute("links", links);
		}
		
		return "/loggedin/meus-links.jsp";
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
