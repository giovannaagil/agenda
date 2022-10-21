package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	// Criando vari�veis encapsuladas para acesso ao banco
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.49.113:3306/agenda";
	private String user = "root";
	private String password = "123@senac";
	
	/**
	 * M�todo respons�vel por abrir uma conex�o com o banco de dados
	 * @return (con)
	 */
	public Connection conectar() {
		// Criar um objeto
		Connection con = null;
		// Tratamento de exce��es
		try {
			// L�gica principal para abrir a conex�o
			// Uso do driver
			Class.forName(driver);
			// Obter os par�metros da conex�o (informa��es do servidor)
			con = DriverManager.getConnection(url, user, password); // Conectar!
			// Retornar a conex�o (Abrir a porta da geladeira)
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
}
