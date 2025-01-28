package controller.command.LoggedCommands;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LinkDaoFactory;
import model.entity.Link;
import model.entity.User;
import model.enums.DaoImplementation;

public class EncurtarLinkCommand implements Command{
	private static final String BASE_URL = "/encurtado.com/link/";

    public static String gerarIdentificadorUUID() {	
    	Random random = new Random();
        int tamanho = 5 + random.nextInt(8);

        String uuid = UUID.randomUUID().toString().replace("-", "");
        
        return uuid.substring(0, tamanho);
    }

    public static String encurtarLink() {
        String identificador = gerarIdentificadorUUID();
        String url = BASE_URL + identificador;
        return url;
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 String urlOriginal = request.getParameter("link");
		    
		 String urlEncurtada = encurtarLink();
		    
		 User usuario = (User) request.getSession().getAttribute("user_id");
		 Link link = new Link();
		 link.setUrl_original(urlOriginal);
		 link.setUrl_encurtada(urlEncurtada);
		    
		 var linkDao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);
		 boolean sucess = linkDao.create(usuario, link);
		    
		 if (sucess) {
		     request.setAttribute("message", urlEncurtada);
		     request.setAttribute("sucess", true);
		 } else {
		     request.setAttribute("message", "Erro ao encurtar o link. Tente novamente.");
		     request.setAttribute("sucess", false);
		 }

		
		return "logged.do?action=pageEncurtador";
	}

}
