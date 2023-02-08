package conexaoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/jdev.posjava";
	private static String password = "alfa.123";
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
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);// Não salvar automáticamente, descidir!
				System.out.println("Conectou com sucesso!");
			}

		} catch (Exception e) {
			System.out.println("===============ERRO=============!");
			e.printStackTrace();
			
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
