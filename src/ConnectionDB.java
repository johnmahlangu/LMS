/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.sql.*;
/**
 *  Connection class for connecting with the database.
 * @author Jonas Mahlangu
 */
public class ConnectionDB 
{
    private static ConnectionDB instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/LMS";
    private String username = "root";
    private String password = "Jt@0843553435";
            
    private ConnectionDB()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);          
        } 
        catch(SQLException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
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
                ex.printStackTrace();
            }
        }   
    }
}
