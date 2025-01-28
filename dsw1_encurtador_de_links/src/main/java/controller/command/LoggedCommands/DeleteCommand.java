package controller.command.LoggedCommands;

import java.io.IOException;

import model.dao.LinkDaoFactory;
import model.entity.Link;
import model.entity.User;
import model.enums.DaoImplementation;
import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String message = "";
		
		var sessao = request.getSession(false);
		
		User user = (User) sessao.getAttribute("user_id");
		
		var dao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Link link = dao.getByID(user, id);
		
		boolean deleted = dao.delete(user, link);
		
		if(deleted) {
			message = "Link deletado com sucesso!";
			request.setAttribute("messageType", "success");
		}else {
			message = "Não foi possivel deletar o link!";
			request.setAttribute("messageType", "error");
		}
		
		request.setAttribute("message", message);
		return "logged.do?action=listLinks";
	}

}
