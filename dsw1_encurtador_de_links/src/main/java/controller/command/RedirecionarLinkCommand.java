package controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LinkDaoFactory;
import model.entity.Link;

public class RedirecionarLinkCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String urlEncurtada = request.getParameter("url_encurtada");
		var dao = new LinkDaoFactory().factory();
		
		if(urlEncurtada != null && !urlEncurtada.isEmpty()) {
			Link link = dao.getByURLencurtada(urlEncurtada);
			
			if(link != null) {
				response.sendRedirect(link.getUrl_original());
				return null; 
			} else {
                request.setAttribute("message", "Link encurtado não encontrado.");
                return "front.do?action=errorPage";
            }
		}
		
		return "front.do?action=errorPage";
	}

}
