package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	private static String banco = "jdbc:postgresql://192.168.31.12/curso-jsp?autoReconnect=true";
	private static String password = "12345678";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
				System.out.println(">>> Conectado ao banco de dados com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(">>> Erro ao conectar com o banco de dados!");
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
