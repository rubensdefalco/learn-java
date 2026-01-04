package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=MEIFacil;encrypt=false";
	private static final String USER = "sa";
	private static final String PASS = "Daimmon!47";

	public static Connection conectar() throws SQLException {
		try {
			return DriverManager.getConnection(URL, USER, PASS);
		}catch (Exception e){
			throw new RuntimeException("Erro na conexão com o Banco", e);
		}

	}
}
