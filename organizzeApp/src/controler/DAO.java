/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Caique
 */
public class DAO {
    public Connection conectar() throws SQLException{
        try{         
            Class.forName("com.mysql.jdbc.Driver");      
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trabjavabancocaique"
            ,"root","");
            Statement statement  = connection.createStatement();
            System.out.println("Banco de DADOS CONECTADOOOO !!");
            return connection;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Banco de Dados não conectado");
        
        }
        return null;       
    }    
}    
