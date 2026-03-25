package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static final String url = "jdbc:mysql://localhost:3306/PISCA";
	public static final String user = "javapisca";
	public static final String password = "2306121984";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}