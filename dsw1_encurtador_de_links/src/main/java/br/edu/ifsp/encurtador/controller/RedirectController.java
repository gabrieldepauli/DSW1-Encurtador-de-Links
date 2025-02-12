package br.edu.ifsp.encurtador.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

import br.edu.ifsp.encurtador.model.dao.AcessoDao;
import br.edu.ifsp.encurtador.model.dao.AcessoDaoFactory;
import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Acesso;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.enums.DaoImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class RedirectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String BASE_URL = "http://localhost:8080/encurtado.com/";
	
	private final LinkDao linkDao;
	private final AcessoDao acessoDao;
	
	public RedirectController() {
		this.linkDao = new LinkDaoFactory().getInstance(DaoImplementation.MYSQL);
		this.acessoDao = new AcessoDaoFactory().getInstance(DaoImplementation.MYSQL);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer urlEncurtada = req.getRequestURL().delete(0, BASE_URL.length());
		
		Link link = linkDao.getByURLencurtada(urlEncurtada.toString());
		
		if (link != null) {
			try {
				String ipAddress = req.getRemoteAddr();
				Acesso acesso = new Acesso();
				acesso.setLinkId(link.getId());
				acesso.setIpCliente(ipAddress);
				acesso.setDataHoraAcesso(LocalDateTime.now());
				acessoDao.create(acesso);
				
				if (link.isPrivateLink()) {
					var user = getLoggedUser(req);
					
					if (user != null && Objects.equals(user.getEmail(), link.getEmailCreator())) {
						resp.sendRedirect(link.getUrlOriginal());
					}
					else {
						req.getRequestDispatcher("/errors/404.jsp").forward(req, resp);
					}
				}
				else {	
					resp.sendRedirect(link.getUrlOriginal());			
				}
			}
			catch(SQLException e) {
				req.getRequestDispatcher("/errors/500.jsp").forward(req, resp);
			}
		}
		else {
			req.getRequestDispatcher("/errors/404.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private User getLoggedUser(HttpServletRequest request) {
		var session = request.getSession(false);
		
		if (session != null) {
			return (User) session.getAttribute("user_id");			
		}
		
		return null;
	}
}
