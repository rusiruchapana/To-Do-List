package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connect {
        public static Connection connect(){
                Connection conn = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ToDoList", "root", "");

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

                return conn;
        }
}
