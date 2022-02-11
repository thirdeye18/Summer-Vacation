package com.ChildrenOfSummer.SummerVacation.Util;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JsonManager {
    static JSONParser parser = new JSONParser();
    static final String DEFAULT_PATH = "./Assets/defaults/";
    static final String CONFIG_PATH = "./Assets/config/";
    static final String SAVE_PATH = "./Assets/save/";

    public static JSONObject getJsonObject(String filename, String fileType) {
        JSONObject jsonObj = null;
        String path;

        // adjust path based on file type
        if(fileType.equalsIgnoreCase("default")) {
            path = DEFAULT_PATH + filename;
        }
        else if(fileType.equalsIgnoreCase("config")) {
            path = CONFIG_PATH + filename;
        }
        else {
            path = SAVE_PATH + filename;
        }

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
            System.out.println(pe);
        }
        return jsonObj;
    }

    public static Map jsonFactory(String filename, String fileType) {
        String path;
        String jsonString = null;

        // adjust path based on file type
        // TODO: organize json files better
        if(fileType.equalsIgnoreCase("default")) {
            path = DEFAULT_PATH + filename;
        }
        else if(fileType.equalsIgnoreCase("config")) {
            path = CONFIG_PATH + filename;
        }
        else {
            path = SAVE_PATH + filename;
        }

        // direct file to JSON string conversion, don't necessary to cast from obj
        try {
            if (Files.exists(Path.of(path))) {
                jsonString = FileUtils.readFileToString(new File(path));
            }
        } catch (IOException e) {
            System.out.println("File could not be loaded!");
            e.printStackTrace();
        }

        ContainerFactory containerFactory = new ContainerFactory() {
            @Override
            public Map createObjectContainer() {
                return new LinkedHashMap<>();
            }
            @Override
            public List creatArrayContainer() {
                return new LinkedList<>();
            }
        };

        try {
            return (Map)parser.parse(jsonString, containerFactory);

        } catch(ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }
        return null;
    }
}
