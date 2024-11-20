import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Layout implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private int WIDTH = 800;
    private int HEIGHT = 700;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;

    JTextArea ta1 = new JTextArea("Text Area 1");
    JTextArea ta2 = new JTextArea("Text Area 2");
    JTextArea ta3 = new JTextArea("Text Area 3");

    public Layout() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Layout layout1 = new Layout();
        layout1.showLayout();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Layout for API");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout(3, 3));

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        //end menu at top

        mainFrame.setJMenuBar(mb); //set menu bar

        scrollPane1 = new JScrollPane(ta1);
        scrollPane2 = new JScrollPane(ta2);
        scrollPane3 = new JScrollPane(ta3);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3)); //set the layout of the panel

        mainFrame.add(controlPanel);

        mainFrame.setVisible(true);
    }

    private void showLayout() {

        JLabel label1 = new JLabel("Image", JLabel.CENTER);
        //JLabel label2 = new JLabel("label 2", JLabel.CENTER);

        JButton button1 = new JButton("Extra Button 1");
        JButton button2 = new JButton("Back");
        JButton button3 = new JButton("Extra Button 2");
        JButton button4 = new JButton("Next");

        button2.setActionCommand("Back");
        button4.setActionCommand("Next");

        // labels don't need ActionCommand or ActionListener
        button2.addActionListener(new ButtonClickListener());
        button4.addActionListener(new ButtonClickListener());

        controlPanel.add(button2);
        controlPanel.add(label1); //label
        controlPanel.add(button4);
        controlPanel.add(scrollPane1);
        controlPanel.add(scrollPane2);
        controlPanel.add(scrollPane3);
        mainFrame.add(button1, BorderLayout.NORTH);
        mainFrame.add(button3, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Next")){
                try {
                    pull();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }



//            if (command.equals("Button 1")) {
//                statusLabel.setText("Button 1 clicked.");
//            } else if (command.equals("Button 2")) {
//                statusLabel.setText("Button 2 clicked.");
//            } else {
//                statusLabel.setText("Cancel Button clicked.");
//            }
        }
    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson= "";
        try {

            URL url = new URL("https://last-airbender-api.fly.dev/api/v1/characters");
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
                System.out.println(name); //prints to dos
                ta1.append((name) + "\n"); //appends to textArea1

                String profession = (String)test.get("profession");
                System.out.println(profession);
                ta2.append((profession) + "\n");

                String gender = (String)test.get("gender");
                System.out.println(gender);

                //getting the array inside our original array

                JSONArray allies = (JSONArray)test.get("allies");

                int x = allies.size();
                for (int y = 0; y < x; ++y){
                    String ally = (String) allies.get(y); //new variable for string called "ally"
                    System.out.println(ally);
                }

                JSONArray enemies = (JSONArray)test.get("enemies");

                int a = enemies.size();
                for (int b = 0; b < a; ++b){
                    String enemy = (String) enemies.get(b); //new variable for string called "enemy"
                    System.out.println(enemy);
                }

            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }



}




