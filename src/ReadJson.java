import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
public class ReadJson {
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma"); //key, value
        file.put("Roll No.", 1704310046); //key, value
        file.put("Tuition Fees", 65400); //key, value


        // To print in JSON format.
        System.out.print(file.get("Tuition Fees"));
        ReadJson readingIsWhat = new ReadJson();

    }

    public ReadJson(){
        try {
            pull();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson= "";
        try {

            URL url = new URL("https://last-airbender-api.fly.dev/api/v1/characters/random");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) parser.parse(totlaJson); //JSONObject is same as the file that we looked at earlier
        System.out.println(jsonArray); //print JSONObject

        try {

            //Array array = (String)jsonArray.get();

//            String name = (String)jsonArray.get("name");
//            String mass = (String)jsonArray.get("mass");
//            String eyeColor = (String)jsonArray.get("eye_color");
//            String birthYear = (String)jsonArray.get("birth_year");
//
//            JSONArray starShips = (JSONArray)jsonArray.get("starships"); //casts JSONArray

            int m = jsonArray.size();
            for (int f = 0; f < m; ++f){
                JSONObject test = (JSONObject) jsonArray.get(f); //gets the object from the array
                System.out.println(test); //prints the object

                String name = (String)test.get("name");
                System.out.println(name);

                String profession = (String)test.get("profession");
                System.out.println(profession);

                String gender = (String)test.get("gender");
                System.out.println(gender);

                //getting the array inside our original array

                JSONArray allies = (JSONArray)jsonArray.get("allies");

                int x = jsonObject.size();
                for (int y = 0; y < x; ++y){
                    String
                }

            }

//            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) JSONArray.get("films");
//            int n =   msg.size(); //(msg).length();
//            for (int i = 0; i < n; ++i) {
//                String test =(String) msg.get(i);
//                System.out.println(test);
//                System.out.println(person.getInt("key"));
//            }

            //Array array1= JSONArray.JSONObject.get;

//            System.out.println(name);
//            System.out.println(mass);
//            System.out.println(eyeColor);
//            System.out.println(birthYear);
           // System.out.println(starShips); //don't need this for assignment
        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
}


