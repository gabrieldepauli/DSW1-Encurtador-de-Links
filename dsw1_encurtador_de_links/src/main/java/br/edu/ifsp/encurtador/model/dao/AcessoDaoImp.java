package br.edu.ifsp.encurtador.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.encurtador.model.connection.DatabaseConnection;
import br.edu.ifsp.encurtador.model.entity.Acesso;
import br.edu.ifsp.encurtador.model.entity.Link;

public class AcessoDaoImp implements AcessoDao {
	
	private static final String INSERT = "INSERT INTO acessos (url_id, ip_cliente, data_hora_acesso) VALUES (?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM acessos WHERE id = ?";
	private static final String SELECT_ALL = "SELECT * FROM acessos WHERE url_id = ? ORDER BY id";

	@Override
	public boolean create(Acesso acesso) {
		if(acesso != null) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
					var preparedStatement = connection.prepareStatement(INSERT)) {
				
				preparedStatement.setInt(1, acesso.getLinkId());
				preparedStatement.setString(2, acesso.getIpCliente());
				preparedStatement.setTimestamp(3, Timestamp.valueOf(acesso.getDataHoraAcesso()));
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
					acesso.setLinkId(result.getInt("id"));
					acesso.setIpCliente(result.getString("ip_cliente"));
					acesso.setDataHoraAcesso(result.getTimestamp("data_hora_acesso").toLocalDateTime());
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
					acesso.setLinkId(result.getInt("url_id"));
					acesso.setIpCliente(result.getString("ip_cliente"));
					acesso.setDataHoraAcesso(result.getTimestamp("data_hora_acesso").toLocalDateTime());
					acessos.add(acesso);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return acessos;
	}

}
