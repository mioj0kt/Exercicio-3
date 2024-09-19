package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    protected Connection conexao;

    // Método para conectar ao banco de dados
    public void conectar() {
        String driverName = "org.postgresql.Driver";  // Nome do driver JDBC do PostgreSQL
        String serverName = "localhost";  // Nome do servidor
        String mydatabase = "petshop";  // Nome do banco de dados
        int porta = 5432;  // Porta do banco de dados
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "postgres";  // Usuário do banco de dados
        String password = "1201";  // Senha do banco de dados
        
        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("O driver JDBC não foi encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados.");
            e.printStackTrace();
        }
    }

    // Método para desconectar do banco de dados
    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão com o banco de dados encerrada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao desconectar do banco de dados.");
            e.printStackTrace();
        }
    }
}
