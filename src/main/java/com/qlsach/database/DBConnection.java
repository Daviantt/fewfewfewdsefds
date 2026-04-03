/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlsach.database;

import java.sql.*;

public class DBConnection {
    private final static String url = "jdbc:mysql://localhost:3306/qlisach";
    private final static String user = "root";
    private final static String password = "quyenthanh1";
    
    public static Connection getDBConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException ex){
            throw new SQLException("Khong tim thay MySQL Driver");
        }
    }
}
