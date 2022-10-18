package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConection {
	private static String url = "jdbc:postgresql://localhost:5432/projeto-jsp?autoReconnnect=true";
	private static String user = "postgres";
	private static String senha = "root";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	
	static { /*Forma para garantir que de qual quer lugar ue a classe for chamada vai ser estabelecido uma conexão*/
		conectar();
	}
	
	public SingleConection() {/*Quando tiver uma instância vai conectar*/
		conectar();
	}
	
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver"); /*Carrega o driver de conexão*/
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false);/*Para não efetuar alterações no banco sem nossso comando*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
