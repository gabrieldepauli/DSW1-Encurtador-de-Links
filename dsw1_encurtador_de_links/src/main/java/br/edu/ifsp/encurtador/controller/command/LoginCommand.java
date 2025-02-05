package br.edu.ifsp.encurtador.controller.command;

import br.edu.ifsp.encurtador.model.dao.UserDao;
import br.edu.ifsp.encurtador.model.dao.UserDaoFactory;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
	
	private final UserDao userDao;
	
	public LoginCommand() {
		this.userDao = new UserDaoFactory().getInstance(DaoImplementation.MYSQL);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
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

	private void createSession(User user, HttpServletRequest request) {
		var session = request.getSession(true);
		session.setAttribute("user_id", user);
		session.setMaxInactiveInterval(24 * 60 * 60);
	}
}
