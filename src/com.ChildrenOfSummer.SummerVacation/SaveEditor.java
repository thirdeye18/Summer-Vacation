package com.ChildrenOfSummer.SummerVacation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;



/*
 *File manager can import, read, write, or create files
 */

public class SaveEditor {

    private static String locationsJSON = "Assets/Locations.JSON";
    private static String NPCsJSON="Assets/NPCs.JSON";



    //business methods

    public static void getAssetFile(String fileName) {
        try {
            String art = "Assets/" + fileName;
            var out = new BufferedOutputStream(System.out);
            Files.copy(Path.of(art), out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * These methods load sets of asset files for different menus.
     */

    public static void menuFiles() {
        getAssetFile("opening-menu.txt");
    }



    /*
     *These methods exist to return SPECIFIC data to the calling method from JSON files.
     */

    public static String getNewLocation(String zone, String location, String direction) {
        /*
         * I hear you. Why oh why are there so many variable declarations?
         * because as best as I can tell, this is the way to drill down into a JSON tree with JSON.simple
         * This function does this:
         * {zone:{location[1]:{directions:{**input**:**data we want**
         * and then returns the data to the game engine.
         *
         * Ask Michael if you need more information.
         */


        String newLocation;
        JSONObject locationJSON = grabJSONData();//THIS IS THE WHOLE JSON FILE
        JSONObject locationZone = (JSONObject) locationJSON.get(zone); //JUST EVERYTHING IN OUR ZONE
        JSONArray locationArea = (JSONArray) locationZone.get(location); //JUST EVERYTHING IN OUR AREA
        JSONObject locationDirection = (JSONObject) locationArea.get(1); //JUST THE DIRECTIONS
        JSONObject locationDirectionCardinal = (JSONObject) locationDirection.get("directions"); //JUST THE DIRECTION I WANT
        newLocation = (String) locationDirectionCardinal.get(direction);

        return newLocation;
    }

    public static String getLocationDescription(String location, String zone) {
        /*
         * FOR NOW:
         * this method just grabs *A* description from the JSON.
         * There is a todo: IF THERE ARE TWO DESCRIPTIONS Check if NPC is present -> if present, give open, else give closed.
         * This is not high on the priority list though so not too concerned if it doesn't get implemented now.
         */

        String descriptionText = null;

        JSONObject locationJSON = grabJSONData();
        JSONObject zoneData = (JSONObject) locationJSON.get(zone); //null
        JSONArray locationData = (JSONArray) zoneData.get(location);
        JSONObject descriptionData = (JSONObject) locationData.get(2);
        while (descriptionText == null) {
            descriptionText = (String) descriptionData.get("description");
            if (descriptionText == null) {
                descriptionText = (String) descriptionData.get("description_open");
                if (descriptionText == null) {
                    descriptionText = (String) descriptionData.get("description_closed");
                }
            }
        }
        System.out.println(descriptionText);

        return descriptionText;
    }

    public static String getNewZone(String location) {
        String zone = null;
        /*
         * This big dumb stupid function checks every zone in the game for the updated player location and then
         * returns the new zone location to the player object so that when you travers zones the game doesn't break.
         * It is very ugly, and can probably be done more efficiently. It was like midnight, and I was like, super tired.
         * Sorry.
         */

        try {
            JSONObject locationData = grabJSONData();
            JSONArray zones = new JSONArray();
            zones.add(locationData.get("Farm"));
            zones.add(locationData.get("Old Town"));
            zones.add(locationData.get("Suburb"));
            zones.add(locationData.get("New Suburb"));
            zones.add(locationData.get("Town Center"));
            zones.add(locationData.get("Wild Field"));
            for (int i = 0; i <= 5; i++) {
                JSONObject tempZone = (JSONObject) zones.get(i);
                if (tempZone.containsKey(location)) {
                    JSONArray tempLoc = (JSONArray) tempZone.get(location);
                    JSONObject insideTempLoc = (JSONObject) tempLoc.get(0);
                    zone = (String) insideTempLoc.get("location_zone");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zone;
    }

    public static JSONArray getNPCsName(String zone, String location) {
        JSONArray NPCname=null;

        JSONObject locationJSON = grabJSONData();//THIS IS THE WHOLE JSON FILE
        JSONObject locationZone = (JSONObject) locationJSON.get(zone); //JUST EVERYTHING IN OUR ZONE
        JSONArray locationArea = (JSONArray) locationZone.get(location); //JUST EVERYTHING IN OUR AREA
        JSONObject NPCshowing = (JSONObject) locationArea.get(4); //JUST THE NPC
        NPCname=(JSONArray) NPCshowing.get("NPCs");

        return NPCname;
    }



    public static String getNPCsDialog (String NPCname) {
        String NPCdia="Yo";

        JSONObject npcJSON = grabNPC();//THIS IS THE WHOLE JSON FILE

        return NPCdia;

    }


    public static JSONArray getLocationItems(String location, String zone){
        /*
         * More drilling down to return the item Array
         *
         * visualization: {zone:{location[3]:{items:[**data we need**]
         *
         * Again, ask Michael for more info.
         */


        JSONObject locationJSON = grabJSONData();
        JSONObject zoneData = (JSONObject) locationJSON.get(zone); //null
        JSONArray locationData = (JSONArray) zoneData.get(location);
        JSONObject itemData = (JSONObject) locationData.get(3);

        JSONArray inventory = (JSONArray) itemData.get("items");
        return inventory;
    }

    public void updateLocationItems(String item, String location, String zone){
        // TODO: 1/31/2022 Finish this method. It should add an item to Player.JSON under "items" and remove from Locations.JSON
        JSONArray areaItems = getLocationItems(location, zone);




        try(FileWriter file = new FileWriter(locationsJSON)){

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void updatePlayerInventory(String item, String verb){

    }

    private static JSONObject grabJSONData() {
        JSONObject locationJSON = null;
        try {
            //create JSON Parser and file reader then create a JSON reader by combining them
            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(locationsJSON);
            Object obj = jsonParser.parse(fileReader);
            locationJSON = (JSONObject) obj;//THIS IS THE WHOLE JSON FILE

        } catch (IOException | ParseException e) {
            System.err.print("Error. Failed to load location.");
        }
        return locationJSON;
    }

    private static JSONObject grabNPC(){
        JSONObject npcJSON = null;
        try {
            //create JSON Parser and file reader then create a JSON reader by combining them
            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(NPCsJSON);
            Object obj = jsonParser.parse(fileReader);
            npcJSON = (JSONObject) obj;//THIS IS THE WHOLE JSON FILE

        } catch (IOException | ParseException e) {
            System.err.print("Error. Failed to load location.");
        }
        return npcJSON;

    }

    public static String getSaveFile() {
        String tempSaveResponse = "getting save file";
        return tempSaveResponse;
    }

}