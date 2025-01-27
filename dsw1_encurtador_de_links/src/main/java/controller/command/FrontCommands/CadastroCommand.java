package controller.command.FrontCommands;

import java.io.IOException;

import controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDaoFactory;
import model.entity.User;

public class CadastroCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var nome = request.getParameter("nome");
		var email = request.getParameter("email");
		var senha = request.getParameter("senha");
		
		var dao = new UserDaoFactory().factory();
		User user = new User(nome, email, senha);
		
		boolean cadastrado = dao.insert(user);
		
		String mensagem;
		if(cadastrado) {
			mensagem = "Usuário cadastrado com sucesso!";
			request.setAttribute("messageType", "success");
		}else {
			mensagem = "Erro ao cadastrar usuário";
			request.setAttribute("messageType", "error");
		}
		
		request.setAttribute("message", mensagem);
		
		return "front.do?action=cadastroForm";
	}

}
