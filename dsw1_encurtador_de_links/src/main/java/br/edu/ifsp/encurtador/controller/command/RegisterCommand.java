package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.dao.UserDao;
import br.edu.ifsp.encurtador.model.dao.UserDaoFactory;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
	
	private final UserDao userDao;
	
	public RegisterCommand() {
		this.userDao = new UserDaoFactory().getInstance(DaoImplementation.MYSQL);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var nome = request.getParameter("nome");
		var email = request.getParameter("email");
		var senha = request.getParameter("senha");		
		User user = new User(nome, email, senha);
		
		boolean cadastrado = userDao.create(user);
		
		if(cadastrado) {
			request.setAttribute("successMessage", "Usuário cadastrado com sucesso!");
		}else {
			request.setAttribute("errorMessage", "Houve um erro durante o cadastro. Por favor, tente novamente.");
		}
				
		return "/cadastro.jsp";
	}
}
