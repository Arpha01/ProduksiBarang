package model;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mucha
 */
public class Koneksi {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String database = "jdbc:mysql://localhost:3306/dbproduksibarang";
    private static final String user = "root";
    private static final String password = "";
    
    private Connection connection;
    private String errorStr;
    
    public String getPesanKesalahan(){
        return errorStr;
    }
    
    public Connection getConnection(){
        connection = null;
        errorStr = "";
        
        try{
            Class.forName(driver);
        }catch(ClassNotFoundException ex){
            errorStr = "Driver JDBC Error " + ex;
        }
        
        if(errorStr.equals("")){
            try{
                connection = DriverManager.getConnection(database+"?user="+user+"&password="+password);
            }catch(SQLException ex){
                errorStr = "Koneksi ke "+database+" gagal\n"+ex;
            }
        }
        
        return connection;
    }
}
