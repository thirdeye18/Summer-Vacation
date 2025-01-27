package com.ChildrenOfSummer.SummerVacation.Util;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JsonHandler {
    static JSONParser parser = new JSONParser();
    static final String DEFAULT_PATH = "./Assets/defaults/";
    static final String CONFIG_PATH = "./Assets/config/";
    static final String JSON_PATH = "./Assets/json/";
    static final String SAVE_PATH = "./Assets/save/";

    public static JSONObject getJsonObject(String filename, String fileType) {
        JSONObject jsonObj = null;
        String path = getPath(filename, fileType);

        try {
            if (Files.exists(Path.of(path))) {
                //create JSON Parser and file reader then create a JSON reader by combining them
                FileReader file = new FileReader(path);
                Object obj = parser.parse(file);
                jsonObj = (JSONObject) obj;//THIS IS THE WHOLE JSON FILE
                file.close();
            }
        } catch (IOException e) {
            System.err.print("Error. File not found.");
        }
        catch (ParseException pe) {
            // exception catches json format errors and returns where the error is
            System.out.println("position: " + pe.getPosition());
            pe.printStackTrace();
        }
        return jsonObj;
    }

    /*
     * Parser for keyword files and other JSON where you expect a String, List combination.
     * Returns null if the JSON file is not found in the specified location.
     * Make sure to check the returned value prior to using to prevent null pointer exception.
     */

    public static Map<String, ArrayList<String>> jsonToMapStringList(String filename, String fileLocation) {
        String path = getPath(filename, fileLocation);
        String jsonString = null;

        // direct file to JSON string conversion, not necessary to cast from obj
        try {
            if (Files.exists(Path.of(path))) {
                jsonString = FileUtils.readFileToString(new File(path));
            }
            else {
                System.out.println(path + " not found.");
            }
        } catch (IOException e) {
            System.out.println("File could not be loaded!");
            e.printStackTrace();
        }

        try {
            return (Map<String, ArrayList<String>>)parser.parse(jsonString);
        }
        catch(ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            pe.printStackTrace();
        }
        return null;
    }

    /*
     * Same as parser above, but for Map<String, List<int>>
     * Make sure to check the returned value prior to using to prevent null pointer exception.
     */

    public static Map<String, ArrayList<Integer>> jsonMapCoordinates(String filename, String fileLocation) {
        String jsonString = null;
        String path = getPath(filename, fileLocation);

        // direct file to JSON string conversion, not necessary to cast from obj?
        try {
            if (path != null && Files.exists(Path.of(path))) {
                jsonString = FileUtils.readFileToString(new File(path));
            }
            else {
                System.out.println(path + " not found.");
            }
        } catch (IOException e) {
            System.out.println("File could not be loaded!");
            e.printStackTrace();
        }

        try {
            return (Map<String, ArrayList<Integer>>)parser.parse(jsonString);
        }
        catch(ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            pe.printStackTrace();
        }
        return null;
    }

    /*
     * creates path variable based on the passed fileLocation and fileName.
     * This will return null when passed an invalid fileLocation.
     * Make sure to check the returned value prior to using to prevent null pointer exception.
     */

    public static String getPath(String filename, String fileLocation) {
        String path = null;

        // adjust path based on file type
        switch (fileLocation.toLowerCase()) {
            case "config":
                path = CONFIG_PATH + filename;
                break;
            case "json":
                path = JSON_PATH + filename;
                break;
            case "default":
                path = DEFAULT_PATH + filename;
                break;
            case "save":
                path = SAVE_PATH + filename;
                break;
            default:
                System.out.println("Invalid file type!");
        }

        return path;
    }

    public static void loadDefaults(){
        // Save game path locations
        String itemLocationJsonPath = "Assets/Save/item_location.JSON";
        String npcLocationsJsonPath = "Assets/Save/npc_location.JSON";
        String playerJsonPath = "Assets/Save/player.JSON";

        // Create JSON objects from the default files
        JSONObject defaultItemLocations = getJsonObject("default_item_location.json", "default");
        JSONObject defaultNpcLocation = getJsonObject("default_NPC_location.json", "default");
        JSONObject newPlayer = getJsonObject("default_player.json", "default");

        // Write JSON objects to the save file location
        writeJSONFile(itemLocationJsonPath, defaultItemLocations);
        writeJSONFile(npcLocationsJsonPath, defaultNpcLocation);
        writeJSONFile(playerJsonPath, newPlayer);
    }

    public static void writeJSONFile(String path, JSONObject obj){
        //write JSON files.
        try (FileWriter w = new FileWriter(path)) {
            w.write(obj.toJSONString());
            w.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * needed this to load the player information to use in the MapLocation function
     */

    public static JSONObject loadGame(){
        //loads Player.JSON for parsing by save method and other load methods outside this class -MS
        return getJsonObject("Player.json", "json");
    }
}
