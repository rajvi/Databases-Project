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

    public void insert(String first_name, String last_name, String phone, String gender, String age, String major,
                       String original_gpa, String current_gpa, String relationship, String crimes, String tattoos,
                       String friends_entered, String duration, String time_entered, String bac, String friends_left, String returned /*,
                       String bars_frequented*/){

        String dquery = "INSERT INTO drinkers (`First Name`, `Last Name`, Phone, Gender, Age, Major, `Original GPA`, `Current GPA`, `Relationship Status`, Crimes, Tattoos, `Amount of Friends Entered With`, `Average Duration (hrs)`, `Time Entered (pm)`, `Average BAC %`, `Amount of Friends Left With`, `Returned Safely?`) VALUES ( " + "'" + first_name + "'"
                + "," + "'" + last_name + "'" + "," + "'" + phone + "'" + "," + "'" + gender + "'" + "," + age + "," + "'" +major + "'" + ","
                + original_gpa + "," + current_gpa + "," + "'" + relationship  + "'" + "," + crimes + "," + tattoos + ","
                + friends_entered + "," + duration + "," + time_entered + "," + bac + "," + friends_left + ","
                +  "'" + returned  + "'" + ")";

        System.out.print(dquery);
        //String fquery = "INSERT INTO frequented ("


        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(dquery);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
