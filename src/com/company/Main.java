package com.company;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import sun.plugin2.os.windows.FLASHWINFO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import static spark.Spark.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        port(80);

        staticFileLocation("/public");
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

        post("/locatebar", (request, response) -> {
            String city = request.queryParams("location");
            System.out.println(city);
            HashMap<String, Object> model = new HashMap<String, Object>();

//            String find_good_bars = "SELECT Name,`Full Address` as addr FROM bars WHERE City = " + "'" + city + "'";
            String find_good_bars = "SELECT DISTINCT f.`bar name` as Name, b.Rating FROM frequents f " +
                    "INNER JOIN drinkers d on d.ID = f.`drinker id` AND (d.`Current GPA` - d.`Original GPA`)>=0 " +
                    "AND d.`Returned Safely?` = 'YES' AND (d.`Amount of Friends Left With` - d.`Amount of Friends Entered With`)>=0 " +
                    "INNER JOIN bars b on f.`bar name` = b.name AND b.city = " + "'" + city + "'" + "AND b.Rating>=4 " +
                    "order by b.Rating desc";
            System.out.println(find_good_bars);
            ResultSet rs = helper.select(find_good_bars);
            String name = "";
            ArrayList<Bar> arr = new ArrayList<Bar>();
            while(rs.next()) {
                arr.add(new Bar(rs.getString("Name"), rs.getInt("Rating")));
            }
            model.put("city",arr);

            return render(model,"/public/locatebar.html");

        });


        post("/drinker",(request, response) -> {
            String user = request.queryParams("username");
            System.out.println(user);
            HashMap<String,Object> model = new HashMap<String, Object>();

            String query = "SELECT ID, `First Name`, Age, Major, `Original GPA`, `Current GPA`, Crimes, " +
                    "`Returned Safely?` FROM drinkers WHERE drinkers.ID = " + "'" + user + "'";
            System.out.println(query);
            ResultSet rs = helper.select(query);
            String name = "";
            int age = 0;
            String major = "";
            float gpa_difference = 0;
            int id = 0;
            int crimes = 0;
            String returned = "";

            while (rs.next()){
                name = rs.getString("First Name");
                age = rs.getInt("Age");
                major = rs.getString("Major");
                gpa_difference = rs.getFloat("Current GPA") - rs.getFloat("Original GPA");
                id = rs.getInt("ID");
                crimes = rs.getInt("Crimes");
                returned = rs.getString("Returned Safely?");
            }

            String message = "";
            if(gpa_difference > 0 && returned.equals("YES") && crimes == 0)
                message = "Keep it up!";
            else if((gpa_difference <= 0.5 && returned.equals("NO")) || (crimes > 3 && returned.equals("NO")))
                message = "Extreme Danger";
            else if(gpa_difference <= 0.5 || crimes > 0)
                message = "Danger";
            else if(gpa_difference < 0 || returned.equals("NO") || crimes > 0)
                message = "Possibility of Danger";
            else if(gpa_difference >= 0 || returned.equals("YES") || crimes== 0)
                message = "You are stable";
            else
                message = "Ok";

            model.put("name", name);
            model.put("age", age);
            model.put("major", major);
            model.put("gpa_difference", gpa_difference);
            model.put("id", id);
            model.put("crimes", crimes);
            model.put("status", message);
            System.out.println("REACHED");
            //String id = request.queryParams("username");

            String frequented_bars = "SELECT `bar name` FROM frequents WHERE `drinker id`= " + "'" + user + "'";
            ResultSet rs2 = helper.select(frequented_bars);
            ArrayList<String> bars = new ArrayList<>();
            while(rs2.next()) {
                bars.add(rs2.getString("bar name"));
            }
            model.put("bars", bars);

//            String recommend_beers = "SELECT distinct s.beer as Beer FROM frequents f, likes l, sells s WHERE f.`bar name` = s.bar AND s.beer = l.beer AND f.`drinker id` = " + "'" + user + "'" + " AND l.`drinker id`= " + "'" + user + "'";
//            System.out.println(recommend_beers);
//            ResultSet rs3 = helper.select(recommend_beers);
//            ArrayList<Beer> beers_liked = new ArrayList<>();
//            while(rs3.next()) {
//                beers_liked.add(new Beer(rs3.getString("Brand")));
//            }
//
//            model.put("beers",beers_liked);

            return render(model,"/public/drinker.html");
        });

        post("/create_account",(request, response) -> {
            HashMap<String,Object> model = new HashMap<String, Object>();
            return render(model,"/public/create_account.html");
        });

        post("/create_account_success",(request, response) -> {
            HashMap<String,Object> model = new HashMap<String, Object>();
            String first_name = request.queryParams("first_name");
            String last_name = request.queryParams("last_name");
//            String phone = request.queryParams("phone");
//            String gender = request.queryParams("gender");
            String phone = "";
            String gender = "";

            String age = request.queryParams("age");
            String major = request.queryParams("major");
            String original_gpa = request.queryParams("original_gpa");
            String current_gpa = request.queryParams("current_gpa");
//            String relationship = request.queryParams("relationship");
            String relationship = "";
            String crimes = request.queryParams("crimes");
//            String tattoos = request.queryParams("tattoos");
//            String friends_entered = request.queryParams("friends_entered");
            String tattoos = "";
            String friends_entered = "";
//            String duration = request.queryParams("duration");
//            String time_entered = request.queryParams("time_entered");
            String duration = "";
            String time_entered = "";
            String bac = request.queryParams("bac");
            String friends_left = request.queryParams("friends_left");


            String returned = request.queryParams("returned");


            helper.insert(first_name, last_name, phone, gender, age, major, original_gpa, current_gpa, relationship,
            crimes, tattoos, friends_entered, duration, time_entered, bac, friends_left, returned);

            String account_id = "SELECT ID FROM drinkers WHERE `First Name`= " + "'" + first_name + "'" + " AND `Last Name` =  " + "'" + last_name + "'";
            ResultSet rs_id = helper.select(account_id);
            String id = "";
            while(rs_id.next()) {
               id = rs_id.getString("ID");
            }
            model.put("id", id);
            //response.redirect("/account_username");
            //response.redirect("/");
            //return "";
            return render(model,"/public/account_username.html");
        });

//        get("/analytics", (req, res) -> {
//            HashMap<String,Object> model = new HashMap<String, Object>();
//            return render(model,"/public/analytics.html");
//        });

        get("/analytics", (request, response) -> {
//            String city = request.queryParams("location");
//            System.out.println(city);
            HashMap<String, Object> model = new HashMap<String, Object>();
            String query1 = "SELECT drinkers.`Average BAC %` as BAC, (drinkers.`Current GPA` - drinkers.`Original GPA`) as GPA FROM drinkers ORDER BY BAC ASC";
            System.out.println(query1);
            ResultSet rs = helper.select(query1);
            List<Float> x_data = new ArrayList<Float>();
            List<Float> y_data = new ArrayList<Float>();
            while(rs.next()) {
                x_data.add(rs.getFloat("BAC"));
                y_data.add(rs.getFloat("GPA"));
            }
            Float[] x_arr = new Float[x_data.size()];
            x_arr = x_data.toArray(x_arr);
            System.out.println(x_arr);
            model.put("x", x_data);


            while(rs.next()) {
                System.out.println("psu");
                System.out.println(rs.getString("GPA"));
                y_data.add(rs.getFloat("GPA"));
            }
            Float[] y_arr = new Float[y_data.size()];
            y_arr = y_data.toArray(y_arr);
            model.put("y", y_data);

            return render(model,"/public/analytics.html");

        });

        get("/patterns", (req, res) -> {
            HashMap<String,Object> model = new HashMap<String, Object>();
            return render(model,"/public/patterns.html");
        });

//        post("/bar",(request, response) -> {
//            HashMap<String,Object> model = new HashMap<String, Object>();
//            return render(model,"/public/bar.html");
//
//        });

    }
    public static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }


}
