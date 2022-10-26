package com.example.digitalwishlist.repository;

import com.example.digitalwishlist.model.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
  private static Connection conn;

  public static Connection getConnection() {
    if (conn != null) {
      return conn;
    }

    String hostname = "jdbc:mysql://localhost:3306";
    String username = "root";
    String password = Password.password;

    try {
      conn = DriverManager.getConnection(hostname, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }
}
