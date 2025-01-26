package controller.command.LoggedCommands;

import java.io.IOException;

import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LinkDaoFactory;
import model.entity.Link;
import model.entity.User;

public class PersonalizarLinkCommand implements Command{
	
	private String encurtarLink(String identificadorPersonalizado) {
        String baseUrl = "https://encurtado.com/";
        
        if (identificadorPersonalizado == null || identificadorPersonalizado.isEmpty()) {
            return null;
        }

        return baseUrl + identificadorPersonalizado;
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String urlOriginal = request.getParameter("link");
		String identificadorPersonalizado = request.getParameter("personalizacao");
		
		if (identificadorPersonalizado != null && identificadorPersonalizado.length() > 12) {
            request.setAttribute("message", "A chave de personalização não pode ter mais de 12 caracteres.");
            request.setAttribute("sucess", false);
            return "logged.do?action=pagePersonalizarLink";  
        }
		
		String urlEncurtada = encurtarLink(identificadorPersonalizado);
		
		if(urlEncurtada != null) {
			User usuario = (User) request.getSession().getAttribute("user_id");
			Link link = new Link();
	        link.setUrl_original(urlOriginal);
	        link.setUrl_encurtada(urlEncurtada);
	        
	        var dao = new LinkDaoFactory().factory();
	        boolean sucesso = dao.create(usuario, link);
	        
	        if (sucesso) {
	            request.setAttribute("message", urlEncurtada);
	            request.setAttribute("sucess", true);
	        } else {
	            request.setAttribute("message", "Erro ao encurtar o link. Tente novamente.");
	            request.setAttribute("sucess", false);
	        }
		}
		
		return "logged.do?action=pagePersonalizarLink";
	}

}
