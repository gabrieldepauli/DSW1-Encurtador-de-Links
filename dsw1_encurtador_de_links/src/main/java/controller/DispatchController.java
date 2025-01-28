package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LinkDao;
import model.dao.LinkDaoFactory;
import model.entity.Link;
import model.enums.DaoImplementation;

@WebServlet("/link/*")
public class DispatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LinkDao linkDao = LinkDaoFactory.getInstance(DaoImplementation.MYSQL);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Link link = linkDao.getByURLencurtada(req.getRequestURI());
		
		if (link != null) {
			resp.sendRedirect(link.getUrl_original());			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
