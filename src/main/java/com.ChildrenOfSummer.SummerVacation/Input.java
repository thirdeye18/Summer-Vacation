package com.ChildrenOfSummer.SummerVacation;

import com.ChildrenOfSummer.SummerVacation.Util.Directions;
import com.ChildrenOfSummer.SummerVacation.Util.JsonHandler;
import com.ChildrenOfSummer.SummerVacation.Util.SoundFX;
import com.ChildrenOfSummer.SummerVacation.Util.TextParser;
import com.ChildrenOfSummer.SummerVacation.view.GamePanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);   //takes direct input currently from the user and passes it to the program
    private static String userInput;
    private static ArrayList<String> empty = new ArrayList<>();
    private static Player player1 = Player.getInstance("default", "Player's House", "Suburb", empty);
    private static GamePanel gamePanel = new GamePanel();

    public static boolean startMenu() {
        /*
         *Start menu, if you type new game it leaves the player1 we instantiate on class load as default or "new"
         *Load will of course over-write those values with the Player.json values so that you can continue your game.
         * -MS
         */
        gamePanel.createGameScreen();
        setupListeners();

        boolean newGame = false;


        while (!newGame) {  //loop until new game option selected, error msg for invalid input

            FileManager.menuFiles();    //display menu text
            String startMenuChoice = TextParser.getVerb(scanner.nextLine());    //.strip().toLowerCase();
            switch (startMenuChoice) {
                case "start":
                    player1.setPlayerInventory(empty);
                    player1.setPlayerName("default");
                    player1.setPlayerLocation("Player's House");
                    player1.setPlayerZone("Suburb");
                    playerCreator();
                    FileManager.loadDefaults();
                    return true;

                case "continue":
                    JSONObject saveFile = FileManager.loadGame();
                    String name = (String) saveFile.get("name");
                    String location = (String) saveFile.get("location");
                    String zone = (String) saveFile.get("zone");
                    ArrayList<String> inventory = (ArrayList<String>) saveFile.get("inventory");
                    player1.setPlayerInventory(inventory);
                    player1.setPlayerName(name);
                    player1.setPlayerLocation(location);
                    player1.setPlayerZone(zone);
                    return false;

                case "quit":
                    System.exit(0);

                default:
                    System.out.println("invalid!\n Please type 'new game' for new game, 'load game' to load your save or 'quit' to quit.\n");
            }

        }
        return newGame;
    }

    //set up listeners
    private static void setupListeners(){

        gamePanel.newGameButton.addActionListener(e -> {
            gamePanel.playerNameScreen();
            player1.setPlayerInventory(empty);
            player1.setPlayerName("default");
            player1.setPlayerLocation("Player's House");
            player1.setPlayerZone("Suburb");
        });


        gamePanel.quitGameButton.addActionListener(e->System.exit(0));

        gamePanel.playerPageEnterGameButton.addActionListener(e-> {
            gamePanel.introScreen();
            userInput = gamePanel.userName.getText();
            player1.setPlayerName(userInput);
            FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
            gamePanel.textField.setText("Hi "+ userInput +"\n"+FileManager.txtFileToString("introduction.txt"));
        });

        gamePanel.mapButton.addActionListener(e -> {
            JFrame frame = new JFrame("Map");
            frame.setSize(400,400);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            ImageIcon mapIcon = new ImageIcon("Assets/img/map.PNG");
            JLabel label = new JLabel(mapIcon);
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        });
        gamePanel.helpButton.addActionListener(e -> {
            JFrame frame = new JFrame("Map");
            frame.setSize(400,400);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            ImageIcon helpIcon = new ImageIcon("Assets/img/help.PNG");
            JLabel label1 = new JLabel(helpIcon);
            frame.add(label1);
            frame.pack();
            frame.setVisible(true);
        });
        gamePanel.musicButton.addActionListener(e -> {
            if (gamePanel.musicButton.isSelected()){
                gamePanel.musicButton.setText("Music On");
                int loop = 3;
                SoundFX.MUSIC1.loopPlay(loop);
            }else {
                gamePanel.musicButton.setText("Music off");
                SoundFX.MUSIC1.stopPlay();
            }
        });

        gamePanel.northButton.addActionListener(e -> goDirection("north"));
        gamePanel.southButton.addActionListener(e -> goDirection("south"));
        gamePanel.eastButton.addActionListener(e -> goDirection("east"));
        gamePanel.westButton.addActionListener(e -> goDirection("west"));

        gamePanel.dropButton.addActionListener(e -> {
            ArrayList<String> locationList = FileManager.getLocationItems(player1.getPlayerLocation());
            ArrayList<String> playerList = FileManager.getPlayerItems();
            int index = gamePanel.inventoryList.getSelectedIndex();
            if (index>=0) {
                String droppedItem = (String) gamePanel.inventoryList.getSelectedValue();
                locationList.add(droppedItem);
                playerList.remove(droppedItem);
                FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                FileManager.savePlayerItems(playerList);
                player1.setPlayerInventory(playerList);
                gamePanel.inventoryListModel.removeElementAt(index);

            }
            else{
                JOptionPane.showMessageDialog(null, "Nothing selected, please select one item to drop", "", JOptionPane.PLAIN_MESSAGE);
            }
        });

        gamePanel.useButton.addActionListener(e -> JOptionPane.showMessageDialog(null,"Nothing happens...", "", JOptionPane.PLAIN_MESSAGE));

        gamePanel.loadGameButton.addActionListener(e -> {
            JSONObject saveFile = FileManager.loadGame();
            String name = (String) saveFile.get("name");
            String location = (String) saveFile.get("location");
            String zone = (String) saveFile.get("zone");
            ArrayList<String> inventory = (ArrayList<String>) saveFile.get("inventory");
            player1.setPlayerInventory(inventory);
            player1.setPlayerName(name);
            player1.setPlayerLocation(location);
            player1.setPlayerZone(zone);
        });


        gamePanel.exploreAirportButton.addActionListener(e ->
                {
                    if (FileManager.getPlayerItems().contains("rope") && !FileManager.getPlayerItems().contains("planks")) {
                        JOptionPane.showMessageDialog(null, "It looks like you have a rope in your bag! You may need something to make a ladder by combining something " +
                                "with the rope! Come back once you got the item!", "", JOptionPane.PLAIN_MESSAGE);
                    } else if (FileManager.getPlayerItems().contains("planks") && !FileManager.getPlayerItems().contains("rope")) {
                        JOptionPane.showMessageDialog(null, "It looks like you have planks in your bag! You may need something to make a ladder by combining something " +
                                "with the planks! Come back once you got the item!", "", JOptionPane.PLAIN_MESSAGE);
                    } else if (FileManager.getPlayerItems().contains("rope") && FileManager.getPlayerItems().contains("planks")) {
                        gamePanel.arriveSpecialScene("scene-one.txt");
                    } else {
                        JOptionPane.showMessageDialog(null, "With no items to help you, you and your friends are caught by security! You're grounded!\nGame Over!", "", JOptionPane.PLAIN_MESSAGE);
                        System.exit(0);
                    }
                }

        );


        gamePanel.arriveSpecialSceneNextButton.addActionListener(e ->
        {
            gamePanel.taskEscapeWithEnoughInventory();
        });

        gamePanel.taskScreenNextButton.addActionListener(e->{
                    gamePanel.mainTextPanel.setVisible(false);
                    gamePanel.taskScreenNextButtonPanel.setVisible(false);
                    gamePanel.largeTextAreaPanel.setVisible(false);
                    gamePanel.locationImgPanel.setVisible(true);
                    gamePanel.mainLocationDescPanel.setVisible(true);
                    gamePanel.userInputPanel.setVisible(true);
                    gamePanel.directionButtonPanel.setVisible(true);
                    gamePanel.inventoryPanel.setVisible(true);

                }
        );

        gamePanel.explorePlayerHouseButton.addActionListener(e->{
                    gamePanel.clearZoneViewPanel();
                    gamePanel.explorePlayerHouseButtonPanel.setVisible(false);
                    gamePanel.taskScreen("scene-two.txt");
                    gamePanel.taskScreenNextButtonPanel.setVisible(true);
                    gamePanel.taskScreenNextButtonPanel.add(gamePanel.taskScreenNextButton);
                    gamePanel.con.add(gamePanel.taskScreenNextButtonPanel);
                    FileManager.sceneWriter(true, "sceneTwoPassed");
                    JOptionPane.showMessageDialog(null,"You arrived and completed task", "", JOptionPane.PLAIN_MESSAGE);
                }
        );

        gamePanel.exploreHayFieldButton.addActionListener(e->{
                    gamePanel.clearZoneViewPanel();
                    gamePanel.explorePlayerHouseButtonPanel.setVisible(false);
                    gamePanel.taskScreen("scene-three.txt");
                    gamePanel.con.add(gamePanel.hayfieldNextButtonPanel);
                    gamePanel.hayfieldNextButtonPanel.setVisible(true);
                    gamePanel.hayfieldNextButtonPanel.add(gamePanel.hayfieldNextButton);
                    gamePanel.exploreHayFieldButtonPanel.setVisible(false);
                    FileManager.sceneWriter(true, "sceneThreePassed");
                    JOptionPane.showMessageDialog(null,"You arrived and completed task", "", JOptionPane.PLAIN_MESSAGE);
                }
        );
        gamePanel.hayfieldNextButton.addActionListener(e-> gamePanel.taskThrowRockWithEnoughInventory()

        );

        gamePanel.findSaraButton.addActionListener(e->{
                    gamePanel.mainTextPanel.setVisible(false);
                    gamePanel.taskScreenNextButtonPanel.setVisible(false);
                    gamePanel.largeTextAreaPanel.setVisible(false);
                    gamePanel.locationImgPanel.setVisible(true);
                    gamePanel.mainLocationDescPanel.setVisible(true);
                    gamePanel.userInputPanel.setVisible(true);
                    gamePanel.directionButtonPanel.setVisible(true);
                    gamePanel.inventoryPanel.setVisible(true);
                    gamePanel.findSaraButtonPanel.setVisible(false);
                }

        );
        gamePanel.exploreOldHouseSouthButton.addActionListener(e->{
                    gamePanel.clearZoneViewPanel();
                    gamePanel.taskScreen("scene-four.txt");
                    gamePanel.exploreOldHouseSouthButtonPanel.setVisible(false);
                    FileManager.sceneWriter(true, "sceneFourPassed");
                    gamePanel.findSuppliesButtonPanel.setVisible(true);
                    gamePanel.con.add(gamePanel.findSuppliesButtonPanel);
                    gamePanel.findSuppliesButtonPanel.add(gamePanel.findSuppliesButton);
                }

        );
        gamePanel.findSuppliesButton.addActionListener(e->{
                    gamePanel.mainTextPanel.setVisible(false);
                    gamePanel.taskScreenNextButtonPanel.setVisible(false);
                    gamePanel.largeTextAreaPanel.setVisible(false);
                    gamePanel.locationImgPanel.setVisible(true);
                    gamePanel.mainLocationDescPanel.setVisible(true);
                    gamePanel.userInputPanel.setVisible(true);
                    gamePanel.directionButtonPanel.setVisible(true);
                    gamePanel.inventoryPanel.setVisible(true);
                    gamePanel.findSuppliesButtonPanel.setVisible(false);
                }

        );
        gamePanel.exploreRiverButton.addActionListener(e->{
                    GamePanel.taskPaddleRiverWithEnoughInventory();
                }

        );

    }

    public static void goDirection(String direction){
        boolean didMove = false;
        Map<String, ArrayList<String>> locations = JsonHandler.jsonToMapStringList("LocationsSimple.json", "json");
        String parsedDirection = direction.substring(0, 1).toUpperCase() + direction.substring(1);

        for (Directions dir : Directions.values()) {
            if (dir.name().equals(direction.toUpperCase())) {
                String tempLocation = FileManager.getNewLocation(player1.getPlayerZone(), player1.getPlayerLocation(), direction);
                if (tempLocation.equals("Off Map")) {
                    JOptionPane.showMessageDialog(null, "you were unable to move " + direction + ".", "", JOptionPane.PLAIN_MESSAGE);
                } else { //success on move
                    player1.setPlayerLocation(tempLocation);
                    player1.setPlayerZone(FileManager.getNewZone(player1.getPlayerLocation()));
                    FileManager.getLocationDescription(player1.getPlayerLocation(), player1.getPlayerZone());

                    JSONArray NPCname = FileManager.getNPCsName(player1.getPlayerLocation());
                    ArrayList<String> npcNames = (ArrayList<String>) NPCname;
                    //display available items for current location
                    if (!FileManager.getLocationItems(tempLocation).isEmpty()) {
                        System.out.println(FileManager.getLocationItems(tempLocation));
                        System.out.println(gamePanel.inventoryList);
                        String seeItems = "You see the following items lying around: ";
                        String result = String.join(",", FileManager.getLocationItems(tempLocation));
                        String itemText = seeItems + "\n" + result;
                        gamePanel.seeItem.setText(itemText + "\n");
                        gamePanel.userInputEnterButton.addActionListener(e->{
                            ArrayList<String> locationList = FileManager.getLocationItems(player1.getPlayerLocation());
                            ArrayList<String> playerList = FileManager.getPlayerItems();
                            userInput = gamePanel.userInput.getText();
                            String verb = TextParser.getVerb(userInput);
                            ArrayList<String> nouns = TextParser.getNouns(userInput);
                            gamePanel.userInput.setText("");
                            switch (verb) {
                                case "inventory":
                                    JOptionPane.showMessageDialog(gamePanel, "Your inventory has: " + playerList, "", JOptionPane.PLAIN_MESSAGE);
                                    break;
                                case "get":
                                    if (locationList.contains(nouns.get(0))) {
                                        locationList.remove(nouns.get(0));
                                        playerList.add(nouns.get(0));
                                        FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                                        FileManager.savePlayerItems(playerList);
                                        player1.setPlayerInventory(playerList);
                                        gamePanel.inventoryListModel.addElement(nouns.get(0));
                                        JOptionPane.showMessageDialog(gamePanel, nouns.get(0) + " has been added to your inventory.", "", JOptionPane.PLAIN_MESSAGE);
                                        String result1 = String.join(",", FileManager.getLocationItems(tempLocation));
                                        String itemText1 = seeItems + "\n" + result1;
                                        gamePanel.seeItem.setText(itemText1 + "\n");
                                    } else {
                                        System.out.println(locationList);
                                        JOptionPane.showMessageDialog(gamePanel, "I can't get that! There's no " + nouns.get(0) + " for me to pick up!", "", JOptionPane.PLAIN_MESSAGE);

                                    }

                                    break;

                                case "drop":
                                    if (playerList.contains(nouns.get(0))) {
                                        locationList.add(nouns.get(0));
                                        playerList.remove(nouns.get(0));
                                        FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                                        FileManager.savePlayerItems(playerList);
                                        player1.setPlayerInventory(playerList);
                                        gamePanel.inventoryListModel.removeElement(nouns.get(0));
                                    } else {
                                        JOptionPane.showMessageDialog(gamePanel,"Sorry, I can't drop what I don't have.", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    break;
                                case "use":
                                    JOptionPane.showMessageDialog(gamePanel,"Nothing happens...", "", JOptionPane.PLAIN_MESSAGE);
                                    break;
                                case "talk":
                                    JOptionPane.showMessageDialog(gamePanel, player1.talk(nouns.get(0)), "", JOptionPane.PLAIN_MESSAGE);
                                    break;
                                case "go":
                                    goDirection(nouns.get(0));
                                    break;
                                case "quit":
                                    FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
                                    System.exit(0);
                                    break;
                                case "":
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(gamePanel, "I didn't understand that command. for help click help button on the top or type help.", "", JOptionPane.PLAIN_MESSAGE);
                            }


                        });

                    } else if (FileManager.getLocationItems(tempLocation).isEmpty()) {
                        gamePanel.seeItem.setText("");
                    }
                    //display available people in current location
                    if (!npcNames.isEmpty()) {
                        String nameThree = null;
                        String nameTwo = null;
                        String name = null;
                        switch (npcNames.size()) {
                            case 3:
                                nameThree = npcNames.get(2);
                            case 2:
                                nameTwo = npcNames.get(1);
                            case 1:
                                name = npcNames.get(0);
                        }
                        switch (npcNames.size()) {
                            case 3:
                                gamePanel.seePeople.setText("You see " + nameThree + ", " + nameTwo + ", and " + name + ".");
                                break;
                            case 2:
                                gamePanel.seePeople.setText("You see " + name + " and " + nameTwo + ".");
                                break;
                            case 1:
                                gamePanel.seePeople.setText("You see " + name + ".");
                                break;
                        }

                    } else {
                        gamePanel.seePeople.setText("");
                    }

                }

                didMove = true;
                String currentZone = player1.getPlayerZone().toLowerCase();
                String zoneImgFileName = "Assets/zone-png/" + currentZone + ".jpg";
                ImageIcon currentZoneImg = new ImageIcon(zoneImgFileName);
                gamePanel.locationImgLabel.setIcon(currentZoneImg);
                gamePanel.locationDesc.setText(FileManager.getLocationDescription(player1.getPlayerLocation(), player1.getPlayerZone()));
                if ((tempLocation.equalsIgnoreCase("player's house")) && FileManager.sceneReader("sceneOnePassed") && !FileManager.sceneReader("sceneTwoPassed")) {
                    gamePanel.directionButtonPanel.setVisible(false);
                    gamePanel.con.add(gamePanel.explorePlayerHouseButtonPanel);
                    gamePanel.explorePlayerHouseButtonPanel.add(gamePanel.explorePlayerHouseButton);

                }
                if ((tempLocation.equalsIgnoreCase("hay field")) && FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed") && !FileManager.sceneReader("sceneThreePassed")) {
                    gamePanel.directionButtonPanel.setVisible(false);
                    gamePanel.exploreHayFieldButtonPanel.setVisible(true);
                    gamePanel.con.add(gamePanel.exploreHayFieldButtonPanel);
                    gamePanel.exploreHayFieldButtonPanel.add(gamePanel.exploreHayFieldButton);

                }
                if ((tempLocation.equalsIgnoreCase("old house south")) && FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed")
                        && FileManager.sceneReader("sceneThreePassed")&&!FileManager.sceneReader("sceneFourPassed")) {
                    gamePanel.directionButtonPanel.setVisible(false);
                    gamePanel.exploreOldHouseSouthButtonPanel.setVisible(true);
                    gamePanel.con.add(gamePanel.exploreOldHouseSouthButtonPanel);
                    gamePanel.exploreOldHouseSouthButtonPanel.add(gamePanel.exploreOldHouseSouthButton);


                }
                if ((tempLocation.equalsIgnoreCase("river")) && FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed")
                        && FileManager.sceneReader("sceneThreePassed")&&FileManager.sceneReader("sceneFourPassed")&&!FileManager.sceneReader("sceneFivePassed")) {
                    gamePanel.directionButtonPanel.setVisible(false);
                    gamePanel.exploreRiverButtonPanel.setVisible(true);
                    gamePanel.con.add(gamePanel.exploreRiverButtonPanel);
                    gamePanel.exploreRiverButtonPanel.add(gamePanel.exploreRiverButton);

                }
            }

        }

        for (ArrayList<String> locArr : locations.values()) {
            if(locArr.contains(parsedDirection.toLowerCase())) {
                String tempLocation = FileManager.getNewLocation(player1.getPlayerZone(), player1.getPlayerLocation(), parsedDirection);
                player1.setPlayerLocation(parsedDirection);
                player1.setPlayerZone(FileManager.getNewZone(player1.getPlayerLocation()));
                FileManager.getLocationDescription(player1.getPlayerLocation(), player1.getPlayerZone());
                JSONArray NPCname= FileManager.getNPCsName(player1.getPlayerLocation());
                ArrayList<String> npcNames = (ArrayList<String>) NPCname;

                //display available items for current location
                if (!FileManager.getLocationItems(parsedDirection).isEmpty()) {
                    System.out.println(FileManager.getLocationItems(tempLocation));
                    String seeItems="You see the following items lying around: ";
                    String result = String.join(",", FileManager.getLocationItems(tempLocation));
                    String itemText = seeItems + "\n" + result;
                    gamePanel.seeItem.setText(itemText+"\n");
                }
                else if(FileManager.getLocationItems(tempLocation).isEmpty()){
                    gamePanel.seeItem.setText("");
                }
                //display available people in current location
                if(!npcNames.isEmpty()){
                    String nameThree = null;
                    String nameTwo = null;
                    String name = null;
                    switch (npcNames.size()){
                        case 3:
                            nameThree = npcNames.get(2);
                        case 2:
                            nameTwo = npcNames.get(1);
                        case 1:
                            name = npcNames.get(0);
                    }
                    switch (npcNames.size()){
                        case 3:
                            gamePanel.seePeople.setText("You see " + nameThree + ", " + nameTwo + ", and " + name + ".");
                            break;
                        case 2:
                            gamePanel.seePeople.setText("You see " + name + " and " + nameTwo + ".");
                            break;
                        case 1:
                            gamePanel.seePeople.setText("You see "+name+".");
                            break;
                    }
                }
                else{
                    gamePanel.seePeople.setText("");
                }
                didMove = true;
            }
        }

        if (!didMove) {
            JOptionPane.showMessageDialog(null,"you were unable to move " + direction + ".","",JOptionPane.PLAIN_MESSAGE);
        }
        FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());

    }

    static void introduction() {
        FileManager.getAssetFile("introduction.txt");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        FileManager.getZoneArtFile("zone2.txt");
        FileManager.getAssetFile("game-start.txt");
    }

    static void playerCreator(){
        /*
         *Takes in your name and saves the save file with default values.
         */
        System.out.print("Enter your name:");
        userInput = scanner.nextLine().strip();
        player1.setPlayerName(userInput);
        FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
    }

    /*
     * THIS big boy is the main interaction point for the user with the game. It takes user commands in as verb x noun.
     * For example, you can type "get the dog" text parser will return [get, dog]
     * TODO: This might be able to go away, will comment out for now
     */

    /*public static void inputCommandsLogic() {
        ArrayList<String> locationList = FileManager.getLocationItems(player1.getPlayerLocation());
        ArrayList<String> playerList = FileManager.getPlayerItems();

        if (!locationList.isEmpty()) {
            System.out.println("You see the following items lying around: ");
            for (String item : locationList) {
                System.out.print("|" + item);
            }
            System.out.println("|");
        }

        // Get user input and parse
        System.out.print("\nWhat would you like to do?");
        userInput = scanner.nextLine();
        //String[] answerWords = ANSWER.split(" ");
        String verb = TextParser.getVerb(userInput);
        ArrayList<String> nouns = TextParser.getNouns(userInput);

        switch (verb) {
            case "map":
                FileManager.getAssetFile("map.txt");
                System.out.println("\nYour current location is " + player1.getPlayerLocation());
                inputCommandsLogic();
                break;
            case "inventory":
                System.out.println("Your inventory has: " + playerList);
                break;
            case "go":
                boolean didMove = false;
                for (Directions dir : Directions.values()) {
                    if (dir.name().equals(nouns.get(0).toUpperCase())) {
                        player1.move(nouns.get(0));
                        didMove = true;
                    }
                }
                if (!didMove) {
                    System.out.println("you were unable to move " + nouns.get(0) + ".");
                }
                FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
                break;
            case "get":
                if (locationList.contains(nouns.get(0))) {
                    locationList.remove(nouns.get(0));
                    playerList.add(nouns.get(0));
                    FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                    FileManager.savePlayerItems(playerList);
                    player1.setPlayerInventory(playerList);
                    System.out.println("Your inventory has: " + playerList);
                } else {
                    System.out.println("I can't get that! There's no " + nouns.get(0) + " for me to pick up!");
                }
                break;
            case "drop":
                if (playerList.contains(nouns.get(0))) {
                    locationList.add(nouns.get(0));
                    playerList.remove(nouns.get(0));
                    FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                    FileManager.savePlayerItems(playerList);
                    player1.setPlayerInventory(playerList);
                } else {
                    System.out.println("I can't drop what I don't have!");
                }
                break;
            case "use":
                //TODO: Currently use does nothing
                System.out.println("Nothing happens...");
                break;
            case "talk":
                System.out.println(player1.talk(nouns.get(0)));
                break;
            case "help":
                System.out.println("Your current location is " + player1.getPlayerLocation());
                FileManager.getAssetFile("help.txt");
                inputCommandsLogic();
                break;
            case "music":
                switch (nouns.get(0)) {
                    case "on":
                        int loop = 3;
                        SoundFX.MUSIC1.loopPlay(loop);
                        System.out.println("Back ground music turned on~~~~~");
                        break;
                    case "off":
                        SoundFX.MUSIC1.stopPlay();
                        System.out.println("Back ground music stopped~~~~~");
                        break;
                    default: System.out.println("Not a valid response\n [on] [off]");
                }

                break;
            case "quit":
                FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
                System.out.println("Press 1 to quit to menu or 2 to exit game:");
                int choice = scanner.nextInt();
                switch (choice){
                    case 2:
                        System.exit(0);
                        break;
                    case 1:
                        GameEngine.execute();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        inputCommandsLogic();
                }

            default:
                System.out.println("I didn't understand that command. for help type help.");
                inputCommandsLogic();

        }
        FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
    }*/

    public static boolean sceneOneAction() {
        boolean sceneOnePass = FileManager.sceneReader("sceneOnePassed");
        ArrayList<String> playerList = FileManager.getPlayerItems();
        System.out.println("You noticed that your rope and planks could be combined!\n " +
                "You can create a ladder to get out!\n " +
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
            FileManager.getAssetFile("scene-one-end.txt");
            FileManager.sceneWriter(true, "sceneOnePassed");
        } else if (scan.equals("no")){
            System.out.println("Game Over. Press enter to continue...");
            scanner.nextLine();
            startMenu();
        }
        else{
            System.out.println("Input not valid, enter yes or no");
            sceneOneAction();
        }
        return sceneOnePass;
    }
    static boolean sceneOneTransition(){
        boolean sceneOnePass = FileManager.sceneReader("sceneOnePassed");
        ArrayList<String> playerList = FileManager.getPlayerItems();
        if (player1.getPlayerLocation().equals("Paine Field") && !sceneOnePass) {
            FileManager.getAssetFile("scene-one.txt");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            if (playerList.contains("rope") && playerList.contains("planks")) {
                sceneOnePass = sceneOneAction();
            } else {
                System.out.println();
                System.out.println("With no items to help you, you and your friends are caught by security! You're grounded!");
                System.out.println("Game Over. Press Enter to continue...");
                scanner.nextLine();
                GameEngine.execute();
            }
        }
        return sceneOnePass;
    }

    static boolean sceneThree(){
        boolean sceneThreePass = FileManager.sceneReader("sceneThreePassed");

        ArrayList<String> playerList = FileManager.getPlayerItems();
        System.out.println(player1.getPlayerLocation());

        if (player1.getPlayerLocation().equals("Hay Field") && !sceneThreePass) {
            if (playerList.contains("rock")) {
                System.out.println("You can throw a rock to escape. Do you want to?");
                String scan = scanner.nextLine().strip().toLowerCase();
                if (scan.equals("yes")) {
                    System.out.println("You've escaped from the farmer.");
                    FileManager.getAssetFile("scene-three-end.txt");
                    sceneThreePass = true;
                    FileManager.sceneWriter(true, "SceneThreePass");
                } else if (scan.equals("no")){
                    System.out.println("You got caught! Game Over. Press enter to continue");
                    scanner.nextLine();
                    startMenu();
                }
                else{
                    System.out.println("Input not valid, enter yes or no");
                }
            } else {
                System.out.println("You reached into your inventory to find something to throw at the farmer" +
                        "but there's nothing there! You've been caught!");
                System.out.println("Game Over. You should explore the map to find something" +
                        "to throw at the farmer to distract him. A rock, maybe? Press enter to continue");
                scanner.nextLine();
                startMenu();
            }
        }
        player1.getPlayerInventory();
        playerList.remove("rock");
        player1.setPlayerInventory(playerList);
        FileManager.savePlayerItems(playerList);
        return sceneThreePass;
    }

    static boolean sceneFive(){
        boolean sceneFivePass = FileManager.sceneReader("sceneFivePassed");
        ArrayList<String> playerList = FileManager.getPlayerItems();
        System.out.println(player1.getPlayerLocation());
        if (playerList.contains("raft")&& playerList.contains("paddle") && playerList.contains("shovel") && !sceneFivePass) {
            FileManager.getAssetFile("scene-five.txt");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            boolean rapidsComplete = false;
            int raftHP = 5;
            while (raftHP > 0 && !rapidsComplete) {
                System.out.println("Rapids rush up to meet you in the middle of the river!\n Which way will you paddle to avoid them?");
                System.out.print("type 'paddle left' or 'paddle right': ");
                userInput = scanner.nextLine().strip().toLowerCase();
                boolean complete = false;
                while (!complete) {
                    switch (userInput) {
                        case "paddle left":
                            System.out.println("You paddle left. A tree branch in the river snags your raft! It takes some damage.");
                            raftHP--;
                            complete = true;
                            break;
                        case "paddle right":
                            System.out.println("You paddle right. A massive boulder stops your progress... \n After dislodging your raft you are free.");
                            complete = true;
                            break;
                        default:
                            System.out.println("You can't do that right now! Type 'paddle right' or 'paddle left'");
                            userInput = scanner.nextLine().strip().toLowerCase();
                    }
                }
                System.out.println("The river curves left\n You need to choose to hug the inner bank of center the raft in the river.");
                System.out.print("type 'hug the inner bank' or 'center the raft': ");
                userInput = scanner.nextLine().strip().toLowerCase();
                complete = false;
                while (!complete) {
                    switch (userInput) {
                        case "hug the inner bank":
                            System.out.println("A rock in the shallow inner bank scrapes your raft! ");
                            raftHP-=2;
                            complete = true;
                            break;
                        case "center the raft":
                            System.out.println("The raft hits no obstacles as you glide around the bend.");
                            complete = true;
                            break;
                        default:
                            System.out.println("You can't do that right now! Type 'hug the inner bank' or 'center the raft'");
                            userInput = scanner.nextLine().strip().toLowerCase();
                    }
                }
                System.out.println("Ahead, thorny branches almost cover the water.\n They will snag you unless you find a way to avoid them!");
                System.out.print("type 'duck under branches' or 'use paddle on branches': ");
                userInput = scanner.nextLine().strip().toLowerCase();
                complete = false;
                while (!complete) {
                    switch (userInput) {
                        case "duck under branches":
                            System.out.println("The branches puncture holes in your raft!");
                            raftHP-=3;
                            complete = true;
                            break;
                        case "use paddle on branches":
                            System.out.println("You push the thorny branches out of the way of your raft.");
                            rapidsComplete = true;
                            complete = true;
                            break;
                        default:
                            System.out.println("You can't do that right now! Type 'duck under branches' or 'use paddle on branches'");
                            userInput = scanner.nextLine().strip().toLowerCase();
                    }
                }
            }
            if(0 >= raftHP) {
                System.out.println("The raft punctures dumping you and Sara into the water!\nYou didn't reach the island.\n Game Over! Press enter to continue...");
                scanner.nextLine();
                startMenu();
            }
            FileManager.getAssetFile("scene-five-end.txt");
            sceneFivePass = true;
            FileManager.sceneWriter(true, "sceneFivePassed");
        }else{
            sceneFivePass = false;
        }
        return sceneFivePass;
    }
}
