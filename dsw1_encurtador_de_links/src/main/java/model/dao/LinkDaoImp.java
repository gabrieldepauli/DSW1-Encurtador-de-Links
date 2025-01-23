package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.dao.connection.DatabaseConnection;
import model.entity.Link;
import model.entity.User;

public class LinkDaoImp implements LinkDao{
	
	private static final String INSERT = "INSERT INTO link (url_original, url_encurtada, email_criador) VALUES (?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM link WHERE id = ? AND email_criador = ?";
	private static final String SELECT_BY_URL_ENCURTADA = "SELECT * FROM link WHERE url_encurtada = ?";
	private static final String SELECT_ALL = "SELECT * FROM link WHERE email_criador = ? ORDER BY id";
	private static final String UPDATE = "UPDATE link SET url_original = ?, url_encurtada = ? WHERE id = ? AND email_criador = ?";
	private static final String DELETE = "DELETE FROM link WHERE id = ? AND email_criador = ?";

	@Override
	public boolean create(User user, Link link) {
		if(link != null) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
					var preparedStatement = connection.prepareStatement(INSERT)) {
				
				preparedStatement.setString(1, link.getUrl_original());
				preparedStatement.setString(2, link.getUrl_encurtada());
				preparedStatement.setString(3, user.getEmail());
				rows = preparedStatement.executeUpdate();
				
				if (rows > 0) {
					user.addLink(link);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rows > 0;
		}
		return false;
	}

	@Override
	public Link getByID(User user, int id) {
		Link link = null;
		if (id > 0) {
			try (var connection = DatabaseConnection.getConnection();
				 var preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
				
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, user.getEmail());

				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					link = new Link();
					link.setId(result.getInt(id));
					link.setUrl_original(result.getString("url_original"));
					link.setUrl_encurtada(result.getString("url_encurtada"));
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
				 var preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
				
				preparedStatement.setString(1, urlEncurtada);

				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					link = new Link();
					link.setId(result.getInt("id"));
					link.setUrl_original(result.getString("url_original"));
					link.setUrl_encurtada(result.getString("url_encurtada"));
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
				link.setUrl_original(result.getString("url_original"));
				link.setUrl_encurtada(result.getString("url_encurtada"));
				user.addLink(link);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user.getLinks();
	}

	@Override
	public boolean delete(User user, Link link) {
		if(link != null) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
					var preparedStatement = connection.prepareStatement(DELETE)) {
				
				preparedStatement.setInt(1, link.getId());
				preparedStatement.setString(2, user.getEmail());
				rows = preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rows > 0;
		}
		return false;
	}

	@Override
	public boolean update(User user, Link updatedLink, int id) {
		if(updatedLink != null && id > 0) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
					var preparedStatement = connection.prepareStatement(UPDATE)) {
				
				preparedStatement.setString(1, updatedLink.getUrl_original());
				preparedStatement.setString(2, updatedLink.getUrl_encurtada());
				preparedStatement.setInt(3, id);
				preparedStatement.setString(4, user.getEmail());
				
				rows = preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rows > 0;
		}
		return false;
	}

}
