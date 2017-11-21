package com.company;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SelectHelper helper = new SelectHelper();
        get("/", (req, res) -> {
//            ResultSet r = helper.select("SELECT distinct RESTAURANT FROM TripAdvisorData");
//
//            ArrayList<String> arr  = new ArrayList<String>();
//            while(r.next()){
//                arr.add(r.getString("RESTAURANT"));
//            }
//
          HashMap<String,Object> model = new HashMap<String, Object>();
//            model.put("data",arr);
//            model.put("test","This is some random string");
            return render(model,"/public/index.html");
        }


        );


        post("/drinker",(request, response) -> {
            String user = request.queryParams("username") ;
            System.out.println(user);
            HashMap<String,Object> model = new HashMap<String, Object>();

            String query = "SELECT `First Name`, Age, Major FROM drinkers WHERE drinkers.ID = " + "'" + user + "'";
            System.out.println(query);
            ResultSet rs = helper.select(query);
            String name = "";
            int age = 0;
            String major = "";

            while (rs.next()){
                name = rs.getString("First Name");
                age = rs.getInt("Age");
                major = rs.getString("Major");

            }
            model.put("name", name);
            model.put("age", age);
            model.put("major", major);
            return render(model,"/public/drinker.html");

        });

        post("/bar",(request, response) -> {
            HashMap<String,Object> model = new HashMap<String, Object>();
            return render(model,"/public/bar.html");

        });

    }
    public static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }


}
