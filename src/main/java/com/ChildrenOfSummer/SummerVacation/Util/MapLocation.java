package com.ChildrenOfSummer.SummerVacation.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/*
 * We can either pass in the String value of the location, then search the Map for the location and print marker
 * at the found coordinates.
 * Other option is to pre-search for the coordinated, then pass them to the function drawing the map and marker.
 */

public class MapLocation extends Canvas {
    // Map<String, ArrayList<Integer>> mapCoordinates = JsonHandler.jsonMapCoordinates("map_coordinates_test.json", "config");
    // Alternative when using ArrayList of String instead of trying to start with numbers in the JSON
    Map<String, ArrayList<String>> mapCoordinates = JsonHandler.jsonToMapStringList("map_coordinates.json", "config");

    public void paint(Graphics map) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image image1 = t.getImage("./Assets/map/map.png");
        Image image2 = t.getImage("./Assets/map/marker.png");
        map.drawImage(image1, 0, 0, this);
        // for (Map.Entry<String, ArrayList<Integer>> entry : mapCoordinates.entrySet()) {
        for (Map.Entry<String, ArrayList<String>> entry : mapCoordinates.entrySet()) {
            // Strange thing here, when starting with strings only need the parseInt(using Map<String, ArrayList<String>)
            // When starting with Map<String, ArrayList<Integer>> somehow values come as longs.
            // Longs are really difficult to get back to ints requiring 2 casts like below.
            // int x = Integer.parseInt(String.valueOf(entry.getValue().get(0)));
            // int y = Integer.parseInt(String.valueOf(entry.getValue().get(1)));

            int x = Integer.parseInt(entry.getValue().get(0));
            int y = Integer.parseInt(entry.getValue().get(1));
            // right now this just prints marker at all coordinates in the json file
            map.drawImage(image2, x, y, this);
        }

    }

    public static void main(String[] args) {
        MapLocation map = new MapLocation();
        JFrame mapFrame = new JFrame("Map");
        mapFrame.add(map);
        mapFrame.setSize(725, 325);
        mapFrame.setVisible(true);//making the frame visible
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

