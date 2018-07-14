package br.com.unicred.jdbc.procedureproject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class ProcedureApplication {

    public static void main(String[] args) throws SQLException {
        
        System.out.println("Preparando conexão...");
        String conexao = "jdbc:sqlserver://UBR3144\\SQLEXPRESS; databaseName=db_curso_unicred; user=sa; password=xongas_1234;";
        Connection con = DriverManager.getConnection(conexao);
        
        System.out.println("Preparando procedure...");
        String sql = "{call dbo.PR_INS_CLIENTE (?, ?, ?, ?)}";
        CallableStatement statement = con.prepareCall(sql);
        statement.setInt(1, 123);
        statement.setString(2, "José Fernando Aquino");
        statement.setString(1, "Cristóvão Colombo");
        statement.registerOutParameter(4, Types.VARCHAR); //Parâmetro de saída.
        
        System.out.println("Executanto procedure...");
        statement.executeUpdate();
        String mensagem = statement.getString(4);
        JOptionPane jPane = new JOptionPane();
        jPane.setMessage(mensagem);
        
        System.out.println("Fechando conexão...");
        statement.close();
        con.close();
    }
}
