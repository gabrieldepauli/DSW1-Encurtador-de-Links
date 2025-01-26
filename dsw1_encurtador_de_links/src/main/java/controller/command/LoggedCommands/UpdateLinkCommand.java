package controller.command.LoggedCommands;

import java.io.IOException;

import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LinkDaoFactory;
import model.entity.Link;
import model.entity.User;

public class UpdateLinkCommand implements Command{
	
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
		
		var sessao = request.getSession(false);
		int idLink = Integer.parseInt(request.getParameter("id"));
		User user = (User) sessao.getAttribute("user_id");
		var dao = new LinkDaoFactory().factory();
		
		String novaURL = request.getParameter("link");
		String novaPersonalizacao = request.getParameter("personalizacao");
		Link link = dao.getByID(user, idLink);
		String linkEncurtado = link.getUrl_encurtada();
		System.out.println(linkEncurtado);
		
		if(novaPersonalizacao != null && !novaPersonalizacao.isEmpty()) {
			if (novaPersonalizacao.length() > 12) {
	            request.setAttribute("message", "A chave de personalização não pode ter mais de 12 caracteres.");
	            request.setAttribute("sucess", false);
	            return "logged.do?action=pageUpdate";  
	        }
			
			linkEncurtado = encurtarLink(novaPersonalizacao);
		}
		
		Link novoLink = new Link();
		novoLink.setUrl_original(novaURL);
		novoLink.setUrl_encurtada(linkEncurtado);
		
		boolean sucesso = dao.update(user, novoLink, idLink);
		
		if (sucesso) {
            request.setAttribute("message", linkEncurtado);
            request.setAttribute("sucess", true);
        } else {
            request.setAttribute("message", "Erro ao modificar o link. Tente novamente.");
            request.setAttribute("sucess", false);
        }
		
		return "logged.do?action=pageUpdate";
	}

}
