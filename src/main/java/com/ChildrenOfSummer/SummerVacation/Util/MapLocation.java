package com.ChildrenOfSummer.SummerVacation.Util;

import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/*
 * We can either pass in the String value of the location, then search the Map for the location and print marker
 * at the found coordinates.
 * Other option is to pre-search for the coordinated, then pass them to the function drawing the map and marker.
 */

public class MapLocation extends Canvas {
    Toolkit t = Toolkit.getDefaultToolkit();

    // Map<String, ArrayList<Integer>> mapCoordinates = JsonHandler.jsonMapCoordinates("map_coordinates_test.json", "config");
    // Alternative when using ArrayList of String instead of trying to start with numbers in the JSON
    Map<String, ArrayList<String>> mapCoordinates = JsonHandler.jsonToMapStringList("map_coordinates.json", "config");

    public void paint(Graphics map) {
        // Pull location from save data
        JSONObject saveData = JsonHandler.loadGame();
        String currentLocation = (String) saveData.get("location");


        // get the 2 images for map and marker
        Image image1 = t.getImage("./Assets/map/map.png");
        Image image2 = t.getImage("./Assets/map/marker.png");
        // drawing just the map
        map.drawImage(image1, 0, 0, this);
        // for (Map.Entry<String, ArrayList<Integer>> entry : mapCoordinates.entrySet()) {
        for (Map.Entry<String, ArrayList<String>> entry : mapCoordinates.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(currentLocation)) {
                // Strange thing here, when starting with strings only need the parseInt(using Map<String, ArrayList<String>)
                // When starting with Map<String, ArrayList<Integer>> somehow values come as longs.
                // Longs are really difficult to get back to ints requiring 2 casts like below.
                // int x = Integer.parseInt(String.valueOf(entry.getValue().get(0)));
                // int y = Integer.parseInt(String.valueOf(entry.getValue().get(1)));
                int x = Integer.parseInt(entry.getValue().get(0));
                int y = Integer.parseInt(entry.getValue().get(1));
                // this should print marker at matched coordinates in the json file
                map.drawImage(image2, x, y, this);
                break;
            }
        }
    }

    public BufferedImage getMarkerImage() {
        BufferedImage image = null;
        try {
            URL url = new URL("file:./Assets/map/marker.png");
            image = ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}

