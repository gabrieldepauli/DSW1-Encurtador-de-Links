package model.dao;

import java.sql.SQLException;

import model.dao.connection.DatabaseConnection;
import model.entity.User;

public class UserDaoImp implements UserDao {

	private static final String INSERT = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM usuario WHERE email = ?";
	
	@Override
	public boolean insert(User user) {
		int rows = 0;
		
		if(user != null) {
			try(var connection = DatabaseConnection.getConnection();
					var statement = connection.prepareStatement(INSERT)) {
				
				statement.setString(1, user.getEmail());
				statement.setString(2, user.getSenhaHash());
				
				rows = statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rows > 0;
	}

	@Override
	public User findByEmail(String email) {
		User user = null;
		
		try(var connection = DatabaseConnection.getConnection();
				var statement = connection.prepareStatement(SELECT_BY_EMAIL)) {
			
			statement.setString(1, email);
			
			var resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				user = new User(resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("senha"), true);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			user = null;
		}
		
		return user;
	}

}
