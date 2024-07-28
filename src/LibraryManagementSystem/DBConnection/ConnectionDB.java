/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem.DBConnection;

import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 *
 * @author Thokozani Mahlangu
 */
public class ConnectionDB 
{
    private static final String CONFIG_FILE = "db_config.properties";
    private static ConnectionDB instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;
            
    private ConnectionDB()
    {
        Properties properties = new Properties();
        
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error getting confuguration file: " + e);
        }
        
        url = properties.getProperty("db.url");
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");
        
        try{           
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);          
        } 
        catch(SQLException | ClassNotFoundException ex)
        {
            System.err.println("Error connecting to the database: " + ex);
        }
    }
    
    public Connection getConnection() 
    {
        return connection;
    }
    
    public static ConnectionDB getInstance() 
    {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;      
    }
    
    public void closeConnection() 
    {
        if (connection != null)
        {
            try{
                connection.close();
            }
            catch (SQLException ex) {
                System.err.println("Error closing connection: " + ex);
            }
        }   
    }
}
