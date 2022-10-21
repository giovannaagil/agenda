package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	// Criando variáveis encapsuladas para acesso ao banco
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.49.113:3306/agenda";
	private String user = "root";
	private String password = "123@senac";
	
	/**
	 * Método responsável por abrir uma conexão com o banco de dados
	 * @return (con)
	 */
	public Connection conectar() {
		// Criar um objeto
		Connection con = null;
		// Tratamento de exceções
		try {
			// Lógica principal para abrir a conexão
			// Uso do driver
			Class.forName(driver);
			// Obter os parâmetros da conexão (informações do servidor)
			con = DriverManager.getConnection(url, user, password); // Conectar!
			// Retornar a conexão (Abrir a porta da geladeira)
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
}
