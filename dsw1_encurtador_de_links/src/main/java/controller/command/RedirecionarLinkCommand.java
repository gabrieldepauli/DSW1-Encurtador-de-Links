package controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AcessoDaoFactory;
import model.dao.LinkDaoFactory;
import model.entity.Acesso;
import model.entity.Link;

public class RedirecionarLinkCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String urlEncurtada = request.getParameter("url_encurtada");
		var daoLink = new LinkDaoFactory().factory();
		var daoAcesso = new AcessoDaoFactory().factory();
		
		if(urlEncurtada != null && !urlEncurtada.isEmpty()) {
			Link link = daoLink.getByURLencurtada(urlEncurtada);
			
			String ipCliente = request.getRemoteAddr();
			
			Acesso acesso = new Acesso();
			acesso.setIp_cliente(ipCliente);
			
			if(link != null) {
				response.sendRedirect(link.getUrl_original());
				boolean acessoCreated = daoAcesso.create(link, acesso);
				return null; 
			} else {
                request.setAttribute("message", "Link encurtado não encontrado.");
                return "front.do?action=errorPage";
            }
		}
		
		return "front.do?action=errorPage";
	}

}
