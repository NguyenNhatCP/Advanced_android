/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CFH;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author lhd
 */
public class Database {

    public static Connection conn = null;

    public void connectSQL() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=qlBanCafe;Username=sa;Password=123;");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=qlBanCafe;Username=sa;Password=123;");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Can't connect to Database", "Warning", 1);
        }
    }

    public ResultSet LoadData(String sql) {
        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void UpdateData(String sql) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
