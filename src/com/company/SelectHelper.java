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

        String url = "jdbc:mysql://databases336proj.cm1qosinmxff.us-east-2.rds.amazonaws.com/barbeerdrinker?user=rajvi&password=asdfpoiu";
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url);


        } catch (Exception e) {
            System.out.println("EXEPCTION");
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
