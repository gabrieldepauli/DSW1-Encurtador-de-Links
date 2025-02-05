package br.edu.ifsp.encurtador.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifsp.encurtador.model.connection.DatabaseConnection;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;

public class LinkDaoImp implements LinkDao{
	
	private static final String INSERT = "INSERT INTO link (url_original, url_encurtada, email_criador, privado) VALUES (?, ?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM link WHERE id = ?";
	private static final String SELECT_BY_URL_ENCURTADA = "SELECT * FROM link WHERE url_encurtada = ?";
	private static final String SELECT_ALL = "SELECT * FROM link WHERE email_criador = ? ORDER BY id";
	private static final String UPDATE = "UPDATE link SET url_original = ?, url_encurtada = ?, privado = ? WHERE id = ? AND email_criador = ?";
	private static final String DELETE = "DELETE FROM link WHERE id = ? AND email_criador = ?";

	@Override
	public boolean create(Link link) {
		if(link != null) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT)) {
				
				preparedStatement.setString(1, link.getUrlOriginal());
				preparedStatement.setString(2, link.getUrlEncurtada());				
				preparedStatement.setString(3, link.getEmailCreator());
				preparedStatement.setBoolean(4, link.isPrivateLink());
				rows = preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rows > 0;
		}
		return false;
	}

	@Override
	public Link getByID(int id) {
		Link link = null;
		if (id > 0) {
			try (var connection = DatabaseConnection.getConnection();
				 var preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
				
				preparedStatement.setInt(1, id);

				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					link = new Link();
					link.setId(result.getInt("id"));
					link.setUrlOriginal(result.getString("url_original"));
					link.setUrlEncurtada(result.getString("url_encurtada"));
					link.setPrivateLink(result.getBoolean("privado"));
					link.setEmailCreator(result.getString("email_criador"));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return link;
	}
	
	@Override
	public Link getByURLencurtada(String urlEncurtada) {
		Link link = null;
		if (urlEncurtada != null && !urlEncurtada.isEmpty()) {
			try (var connection = DatabaseConnection.getConnection();
				 var preparedStatement = connection.prepareStatement(SELECT_BY_URL_ENCURTADA)){
				
				preparedStatement.setString(1, urlEncurtada);

				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					link = new Link();
					link.setId(result.getInt("id"));
					link.setUrlOriginal(result.getString("url_original"));
					link.setUrlEncurtada(result.getString("url_encurtada"));
					link.setPrivateLink(result.getBoolean("privado"));
					link.setEmailCreator(result.getString("email_criador"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return link;
	}

	@Override
	public List<Link> getAllLinks(User user) {
		user.clearLinks();

		try (var connection = DatabaseConnection.getConnection();
			 var preparedStatement = connection.prepareStatement(SELECT_ALL)){
			
			preparedStatement.setString(1, user.getEmail());
			var result = preparedStatement.executeQuery();

			while (result.next()) {
				var link = new Link();
				link.setId(result.getInt("id"));
				link.setUrlOriginal(result.getString("url_original"));
				link.setUrlEncurtada(result.getString("url_encurtada"));
				link.setPrivateLink(result.getBoolean("privado"));
				user.addLink(link);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user.getLinks();
	}

	@Override
	public boolean delete(Link link) {
		if(link != null) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
					var preparedStatement = connection.prepareStatement(DELETE)) {
				
				preparedStatement.setInt(1, link.getId());
				preparedStatement.setString(2, link.getEmailCreator());
				
				rows = preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rows > 0;
		}
		return false;
	}

	@Override
	public boolean update(Link updatedLink) {
		if(updatedLink != null && updatedLink.getId() > 0) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
					var preparedStatement = connection.prepareStatement(UPDATE)) {
				
				preparedStatement.setString(1, updatedLink.getUrlOriginal());
				preparedStatement.setString(2, updatedLink.getUrlEncurtada());
				preparedStatement.setBoolean(3, updatedLink.isPrivateLink());
				preparedStatement.setInt(4, updatedLink.getId());
				preparedStatement.setString(5, updatedLink.getEmailCreator());
				
				rows = preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rows > 0;
		}
		return false;
	}

}
