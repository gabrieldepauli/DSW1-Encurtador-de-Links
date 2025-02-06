package br.edu.ifsp.encurtador.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import br.edu.ifsp.encurtador.model.connection.DatabaseConnection;
import br.edu.ifsp.encurtador.model.entity.User;

public class UserDaoImp implements UserDao {

	private static final String INSERT = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM usuario WHERE email = ?";
	
	@Override
	public boolean create(User user) throws SQLException  {
		int rows = 0;
		
		if(user != null) {
			try(var connection = DatabaseConnection.getConnection();
					var statement = connection.prepareStatement(INSERT)) {
				
				statement.setString(1, user.getNome());
				statement.setString(2, user.getEmail());
				statement.setString(3, user.getSenhaHash());
				
				rows = statement.executeUpdate();
			} 
			catch (SQLIntegrityConstraintViolationException e) {
				throw new SQLException("Este email já está sendo usado por outro usuário.");
			}
			catch(SQLException e) {
				throw new SQLException("Ops, houve um erro ao registrar você. Por favor, verifique se todos os campos estão preenchidos corretamente.");
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
