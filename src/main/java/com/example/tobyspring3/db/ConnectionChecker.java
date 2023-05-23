package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectionChecker {
    Map<String, String> env = getenv();
    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");

    public void select() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,
                dbUser,
                dbPassword
        );

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");

        while (rs.next()) {
            String col1 = rs.getString(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            System.out.printf("%s %s %s\n", col1, col2, col3);
        }

    }
    public void check() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,
                dbUser,
                dbPassword
        );

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show databases");

        while (rs.next()) {
            String line = rs.getString(1);
            System.out.println(line);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,
                dbUser,
                dbPassword
        );

        PreparedStatement pstmt = conn.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)"
        );
        pstmt.setString(1, "1");
        pstmt.setString(2, "Chaeyeon");
        pstmt.setString(3, "12345678");
        pstmt.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker cc = new ConnectionChecker();
        cc.select();
    }
}
