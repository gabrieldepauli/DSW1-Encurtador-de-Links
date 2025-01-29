package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.dao.UserDao;
import br.edu.ifsp.encurtador.model.dao.UserDaoFactory;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserCommand implements Command {
	
	private final UserDao userDao = UserDaoFactory.getInstance(DaoImplementation.MYSQL);
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("login".equals(action)) { 
			return login(request);
		}
		else if ("cadastrar".equals(action)) {
			return cadastrar(request);
		}
		else if ("logout".equals(action)) {
			return logout(request);
		}
		
		return null;
	}
	
	private String login(HttpServletRequest request) {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		var user = userDao.findByEmail(email);
		
		var authenticated = User.autenticate(user, email, senha);
		
		String view;
		
		if(authenticated) {
			createSession(user, request);
			view = "/loggedin/logged.jsp";
		} else {
            request.setAttribute("errorMessage", "Usuário ou senha inválido.");
			view = "/login.jsp";
		}
		
		return view;
	}
	
	private String cadastrar(HttpServletRequest request) {
		var nome = request.getParameter("nome");
		var email = request.getParameter("email");
		var senha = request.getParameter("senha");		
		User user = new User(nome, email, senha);
		
		boolean cadastrado = userDao.insert(user);
		
		if(cadastrado) {
			request.setAttribute("successMessage", "Usuário cadastrado com sucesso!");
		}else {
			request.setAttribute("errorMessage", "Houve um erro durante o cadastro. Por favor, tente novamente.");
		}
				
		return "/cadastro.jsp";
	}
	
	private String logout(HttpServletRequest request) {
		var session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		
		return "/login.jsp";
	}
	
	private void createSession(User user, HttpServletRequest request) {
		var session = request.getSession(true);
		session.setAttribute("user_id", user);
		session.setMaxInactiveInterval(24 * 60 * 60);
	}
}
