package com.ChildrenOfSummer.SummerVacation;

import org.json.simple.JSONObject;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);   //takes direct input currently from the user and passes it to the program
    private static String ANSWER;
    private static ArrayList<String> empty = new ArrayList<>();
    private static Player player1;
    private static final Clip clip = FileManager.getMusic(null);


    public static boolean startMenu() {
        /*
         *Start menu, if you type new game it leaves the player1 we instantiate on class load as default or "new"
         *Load will of course over-write those values with the Player.json values so that you can continue your game.
         * -MS
         */

        boolean newGame = false;
        FileManager.menuFiles();
        String startMenuChoice = scanner.nextLine().strip().toLowerCase();
        switch (startMenuChoice) {
            case "new game":
                player1 = Player.getInstance("default", "Player's House", "Suburb", empty);
                playerCreator();
                FileManager.loadDefaults();
                newGame = true;
                break;
            case "load game":
                JSONObject saveFile = FileManager.loadGame();
                String name = (String) saveFile.get("name");
                String location = (String) saveFile.get("location");
                String zone = (String) saveFile.get("zone");
                ArrayList<String> inventory = (ArrayList<String>) saveFile.get("inventory");
                player1 = Player.getInstance(name, location, zone, inventory);
                break;
            case "quit":
                System.exit(0);
            default:
                System.out.println("invalid!\n Please type 'new game' for new game, 'load game' to load your save or 'quit' to quit.\n");
                startMenu();
        }
        return newGame;
    }


    static void playerCreator(){
        /*
         *Takes in your name and saves the save file with default values.
         */
        System.out.print("Enter your name:");
        ANSWER = scanner.nextLine().strip();
        player1.setPlayerName(ANSWER);
        FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());

    }

    public static void inputCommandsLogic() {
        /*
         * THIS big boy is the main interaction point for the user with the game. It takes user commands in as verb x noun.
         * For example, you can type "get the dog" and the method will store [get, the, dog]
         * We then switch over the word in position 0 for matches to our commands list ("get") and then execute the "noun"
         * which is really just the word stored in position .length-1. With input validation provided for the verb by the switch statement
         * and for the noun by some robust if->else or for loops, we can prevent the player from breaking the program with an
         * unknown command. -MS
         */
         ArrayList<String> locationList = FileManager.getLocationItems(player1.getPlayerLocation());
         ArrayList<String> playerList = FileManager.getPlayerItems();
        if (!locationList.isEmpty()) {
            System.out.println("You see the following items on the ground: ");
            for (String item : locationList) {
                System.out.print("|" + item);
            }
            System.out.println("|");
        }
        System.out.print("What would you like to do?");

        ANSWER = scanner.nextLine().strip().toLowerCase();
        String[] answerWords = ANSWER.split(" ");
        String verb = answerWords[0];
        String noun1 = "out of bounds saver";
        if (answerWords.length > 1) {
            noun1 = answerWords[1]; //ONLY USED FOR COMBINING
        }
        String noun2 = answerWords[answerWords.length - 1];



        switch (verb) {
            case "map":
                FileManager.getAssetFile("map.txt");
                System.out.println("\nYour current location is " + player1.getPlayerLocation());
                break;
            case "go":
                boolean didMove = false;
                for (Directions dir : Directions.values()) {
                    if (dir.name().equals(noun2.toUpperCase())) {
                        player1.move(noun2);
                        didMove = true;
                    }
                }
                if (!didMove) {
                    System.out.println("you were unable to move " + noun2 + ".");
                }
                FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
                break;
            case "get":
                if (locationList.contains(noun2)) {
                    locationList.remove(noun2);
                    playerList.add(noun2);
                    FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                    FileManager.savePlayerItems(playerList);
                    player1.setPlayerInventory(playerList);
                } else {
                    System.out.println("I can't get that! There's no " + noun2 + " for me to pick up!");
                }
                break;
            case "drop":
                if (playerList.contains(noun2)) {
                    locationList.add(noun2);
                    playerList.remove(noun2);
                    FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                    FileManager.savePlayerItems(playerList);
                    player1.setPlayerInventory(playerList);
                } else {
                    System.out.println("I can't drop what I don't have!");
                }
                break;
            case "combine":
                if (playerList.contains(noun2) && playerList.contains(noun1))
                    if ((noun1.equals("planks") || noun1.equals("rope")) && (noun2.equals("rope") || noun2.equals("planks"))) {
                        System.out.println("You tie the planks to each other using the rope to create a ladder!");
                        playerList.remove(noun1);
                        playerList.remove(noun2);
                        playerList.add("ladder");
                        FileManager.savePlayerItems(playerList);
                        player1.setPlayerInventory(playerList);
                    } else {
                        System.out.println("I can't combine that!");
                    }
                break;
            case "use":
                //do final stuff
                System.out.println("Nothing happens...");
                break;
            case "talk":
                System.out.println(player1.talk(noun2));
                break;
            case "help":
                System.out.println("Your current location is " + player1.getPlayerLocation());
                FileManager.getAssetFile("help.txt");
                break;

            case "music":
                switch (noun2) {
                    case "on":
                        int loopTimes = 3;
                        clip.loop(loopTimes);
                        System.out.println("Back ground music turned on~~~~~");
                        break;
                    case "off":
                        clip.stop();
                        System.out.println("Back ground music stopped~~~~~");
                        break;
                    default: System.out.println("Not a valid response\n [on] [off]");
                }

                break;
            case "quit":
                FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
                System.exit(0);
            default:
                System.out.println("I didn't understand that command. for help type help.");
        }
        if (!playerList.isEmpty()) {
            System.out.println("Your inventory has: " + playerList);
        }
       //"recursion" happens in the while loop of the scene
    }

    static boolean sceneOneTransition(){
        boolean sceneOnePass = FileManager.sceneReader("sceneOnePassed");
        ArrayList<String> playerList = FileManager.getPlayerItems();
        if (player1.getPlayerLocation().equals("Paine Field") && !sceneOnePass) {
            FileManager.getAssetFile("scene-one.txt");
            if (playerList.contains("rope") && playerList.contains("planks")) {
                System.out.println("You noticed that you have a rope and some planks. " +
                        "You can create a ladder to get out. " +
                        "Do you wish to combine the items to get out?");
                String scan = scanner.nextLine().strip();
                if (scan.equals("yes")) {
                    sceneOnePass = true;
                    player1.getPlayerInventory();
                    playerList.remove("rope");
                    playerList.remove("planks");
                    playerList.add("ladder");
                    player1.setPlayerInventory(playerList);
                    FileManager.savePlayerItems(playerList);
                    GameEngine.sceneOneEnd();
                } else if (scan.equals("no")){
                    System.out.println("Game Over. Press enter to continue");
                    scanner.nextLine();
                    startMenu();
                }
                else{
                    System.out.println("Input not valid, enter yes or no");
                }
            } else {
                System.out.println("Game Over. Press enter to continue");
                scanner.nextLine();
                startMenu();
            }
        }
        return sceneOnePass;
    }

    static boolean sceneThree(){
        boolean sceneThreePass = FileManager.sceneReader("sceneTwoPassed");
        ArrayList<String> playerList = FileManager.getPlayerItems();
        if (player1.getPlayerLocation().equals("Barn") && !sceneThreePass) {
            FileManager.getAssetFile("scene-two-end (barn scene).txt");
            if (playerList.contains("ladder") && playerList.contains("rock")) {
                System.out.println("You can climb up the barn using the ladder. Do you want to?");
                String scan = scanner.nextLine().strip();
                if (scan.equals("yes")) {
                    sceneThreePass = true;
                    player1.getPlayerInventory();
                    playerList.remove("ladder");
                    player1.setPlayerInventory(playerList);
                    FileManager.savePlayerItems(playerList);
                    GameEngine.sceneThreeEnd();
                } else if (scan.equals("no")){
                    System.out.println("You got caught! Game Over. Press enter to continue");
                    scanner.nextLine();
                    startMenu();
                }
                else{
                    System.out.println("Input not valid, enter yes or no");
                }
            } else {
                System.out.println("Game Over. Press enter to continue");
                scanner.nextLine();
                startMenu();
            }
        }
        return sceneThreePass;
    }
}
