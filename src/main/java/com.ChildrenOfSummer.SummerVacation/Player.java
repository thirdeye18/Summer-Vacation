package com.ChildrenOfSummer.SummerVacation;

import com.ChildrenOfSummer.SummerVacation.view.GamePanel;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static Player singleton = null;
    private String playerZone;
    private String playerLocation;
    private String playerName;
    private ArrayList<String> playerInventory;
    private int playerMoney;


    //singleton get instance method to prevent multiple player creations
    public static Player getInstance(String playerName, String playerLocation, String playerZone, ArrayList<String> playerInventory){
        if (singleton == null){
            singleton = new Player(playerName,playerLocation,playerZone,playerInventory);
        }
        return singleton;
    }


    //private constructor
    Player(String playerName, String playerLocation, String playerZone, ArrayList<String> playerInventory) {
        this.playerZone = playerZone;
        this.playerLocation = playerLocation;
        this.playerName = playerName;
        this.playerInventory = playerInventory;
    }

    //getters and setters
    public String getPlayerZone() {
        return playerZone;
    }

    public void setPlayerZone(String playerZone) {
        this.playerZone = playerZone;
    }

    public String getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(String playerLocation) {
        // capitalize first letter to match json locations
        this.playerLocation = playerLocation;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<String> getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(ArrayList<String> playerInventory) {
        this.playerInventory = playerInventory;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    //business methods

    void sleep(){
        GameClock.incrementNextDay();
    }

    public void move(String direction){
        /*
         * We create a temp location, and make sure that movement is valid for the player (not off map)
         * If so, we execute the update, which involves getting a location description and any nearby NPCS
         * so the player can interact with their surroundings -MS
         */

        String tempLocation = FileManager.getNewLocation(playerZone, playerLocation, direction);

        if (tempLocation.equals("Off Map")){
            //System.out.println("You can't go that way!");
        }else { //success on move

            playerLocation = tempLocation;
            playerZone = FileManager.getNewZone(playerLocation);
            JSONArray NPCname= FileManager.getNPCsName(playerLocation);
            ArrayList<String> npcNames = (ArrayList<String>) NPCname;

            switch (playerZone){
                case "Wild Field":
                    FileManager.getZoneArtFile("zone1.txt");
                    break;
                case "Suburb":
                    FileManager.getZoneArtFile("zone2.txt");
                    break;
                case "New Suburb":
                    FileManager.getZoneArtFile("zone3.txt");
                    break;
                case "Farm":
                    FileManager.getZoneArtFile("zone4.txt");
                    break;
                case "Town Center":
                    FileManager.getZoneArtFile("zone5.txt");
                    break;
                case "Old Town":
                    FileManager.getZoneArtFile("zone6.txt");
                    break;
            }
            FileManager.getLocationDescription(playerLocation, playerZone);
            if(!npcNames.isEmpty()){
                String name = null;
                switch (npcNames.size()){
                    case 1:
                        name = npcNames.get(0);
                }
                switch (npcNames.size()){

                    case 1:
                        //System.out.println("You see "+name+".");
                    break;
                }
            }
        }

    }



    public String talk(String npcName){
        int number = randomNumberGenerator();
        String dig;
        npcName=npcName.substring(0, 1).toUpperCase() + npcName.substring(1);

        while (number >3||number<1) {
            number = randomNumberGenerator();
        }
        dig=(FileManager.getNPCsDialog(npcName,number));
        return dig;
    }

    public static int randomNumberGenerator(){
        //1-3, 3-6, 1-6
        return (int)(Math.random() * ((5) + 1)+1);
    }
}