package autopecas.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() {
		try {
		final String url = "jdbc:postgresql://localhost:5432/DbDesafioJava?currentSchema = AutoPeças";
		final String usuario = "postgres";
		final String senha = "bruno123";
		
		return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			System.err.print("Erro ao conectar com o banco de dados");
			System.err.println(e.getMessage());
		}
		return null;
	}
}
