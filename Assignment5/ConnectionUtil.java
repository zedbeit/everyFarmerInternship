package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection connect() {
        String url = "jdbc:mysql://localhost:3306/office_schema?serverTimezone=UTC";
        String password = "secret";
        String user = "deehaz";

        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
