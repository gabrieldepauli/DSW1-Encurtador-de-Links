package controller.command.LoggedCommands;

import java.io.IOException;
import java.util.List;

import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AcessoDaoFactory;
import model.dao.LinkDaoFactory;
import model.entity.Acesso;
import model.entity.Link;
import model.entity.User;
import model.enums.DaoImplementation;

public class ListarAcessosCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var dao = new AcessoDaoFactory().factory();
		var linkDao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);

		var sessao = request.getSession(false);
		
		User user = (User) sessao.getAttribute("user_id");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Link link = linkDao.getByID(user, id);
		
		List<Acesso> acessos = dao.getAllAcessos(link);
		
		if(acessos.isEmpty()) {
			request.setAttribute("message", "Sua lista de acessos está vazia!");
			request.setAttribute("messageType", "error");
		}	
		
		request.setAttribute("lista_acessos", acessos);
		return "logged.do?action=pageAcessos";
	}
}
