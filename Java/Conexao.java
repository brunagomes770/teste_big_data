package teste_bigdata_twitter;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
    private static final String USUARIO = "bruna";
    private static final String SENHA = "bruna";
    private static final String URL = "jdbc:mysql://localhost:3306/tweets?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    // Conectar ao banco
    public static Connection abrir() throws Exception {
        // Registrar o driver
        Class.forName(DRIVER);
        // Capturar a conexão
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        // Retorna a conexao aberta
        return conn;


    }

}
