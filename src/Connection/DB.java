/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Rith Record
 */
public class DB {
    public static Connection com;
    public static final String url="jdbc:mysql://localhost:3306/dictionary?useUnicode=yes&characterEncoding=UTF-8";
    public static final String UserName="Chandarith";
    public static final String Password="Darith12345";
    
    public static Connection  dictionary() throws ClassNotFoundException, SQLException
    {
        
        Class.forName("com.mysql.jdbc.Driver");
        com = (Connection) DriverManager.getConnection(url,UserName,Password);
        return com;
        
    }
    
}
