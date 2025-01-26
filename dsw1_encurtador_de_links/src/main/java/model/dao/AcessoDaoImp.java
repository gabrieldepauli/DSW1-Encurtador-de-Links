package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.dao.connection.DatabaseConnection;
import model.entity.Acesso;
import model.entity.Link;

public class AcessoDaoImp implements AcessoDao{
	
	private static final String INSERT = "INSERT INTO acessos (url_id, ip_cliente) VALUES (?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM acessos WHERE id = ?";
	private static final String SELECT_ALL = "SELECT * FROM acessos WHERE url_id = ? ORDER BY id";

	@Override
	public boolean create(Link link, Acesso acesso) {
		if(acesso != null) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
					var preparedStatement = connection.prepareStatement(INSERT)) {
				
				preparedStatement.setInt(1, link.getId());
				preparedStatement.setString(2, acesso.getIp_cliente());
				rows = preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rows > 0;
		}
		return false;
	}

	@Override
	public Acesso getByID(int id) {
		Acesso acesso = null;
		if (id > 0) {
			try (var connection = DatabaseConnection.getConnection();
				 var preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
				
				preparedStatement.setInt(1, id);

				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					acesso = new Acesso();
					acesso.setId(result.getInt("id"));
					acesso.setIp_cliente(result.getString("ip_cliente"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return acesso;
	}

	@Override
	public List<Acesso> getAllAcessos(Link link) {
		List<Acesso> acessos = new LinkedList<Acesso>();
		
		try (var connection = DatabaseConnection.getConnection();
				 var preparedStatement = connection.prepareStatement(SELECT_ALL)){
				
				preparedStatement.setInt(1, link.getId());
				var result = preparedStatement.executeQuery();

				while (result.next()) {
					var acesso = new Acesso();
					acesso.setId(result.getInt("id"));
					acesso.setIp_cliente(result.getString("ip_cliente"));
					acessos.add(acesso);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return acessos;
	}

}
