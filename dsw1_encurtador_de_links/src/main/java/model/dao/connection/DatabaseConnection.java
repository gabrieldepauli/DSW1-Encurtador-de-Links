package model.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/encurtador_db";

	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	private DatabaseConnection() {
	}
	
	private static final String RESOURCE = "java:/comp/env/jdbc/mysql";

	public static Connection getConnection() throws SQLException {
		
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(RESOURCE);
			
			return dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
