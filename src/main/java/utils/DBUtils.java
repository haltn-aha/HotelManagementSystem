/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        // Chuỗi kết nối chuẩn database PRJ_Group4
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ_Group4;encrypt=true;trustServerCertificate=true";
        String user = "sa"; 
        String password = "123456"; 
        
        return DriverManager.getConnection(url, user, password);
    }
}