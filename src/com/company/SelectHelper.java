package com.company;

import com.sun.istack.internal.Nullable;

import java.sql.*;

/**
 * Created by Rajvi on 11/17/17.
 */
public class SelectHelper {
    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/Users/Rajvi/Documents/Rutgers/cs336/Trip Advisor.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the warehouses table
     */
    public  void selectAll() {
        String sql = "SELECT RESTAURANT FROM TripAdvisorData";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("RESTAURANT"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * SQL helper
     * @param query our query
     *
     *
     */

    public ResultSet select(String query){





        try{
            Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query);

            // loop through the result set
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }




}
