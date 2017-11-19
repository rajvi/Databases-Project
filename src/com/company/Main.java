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
            ResultSet r = helper.select("SELECT distinct RESTAURANT FROM TripAdvisorData");
            ArrayList<String> arr  = new ArrayList<String>();
            while(r.next()){
                arr.add(r.getString("RESTAURANT"));
            }

            HashMap<String,Object> model = new HashMap<String, Object>();
            model.put("data",arr);
            model.put("test","This is some random string");
            return render(model,"/public/index.html");
        }


        );


        post("/student",(request, response) -> {
            String user = request.queryParams("username") ;
            HashMap<String,Object> model = new HashMap<String, Object>();
            return render(model,"/public/student.html");

        });

        post("/company",(request, response) -> {
            HashMap<String,Object> model = new HashMap<String, Object>();
            return render(model,"/public/company.html");

        });

    }
    public static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }


}
