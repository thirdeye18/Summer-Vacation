package com.ChildrenOfSummer.SummerVacation;

import com.ChildrenOfSummer.SummerVacation.Util.*;
import com.ChildrenOfSummer.SummerVacation.view.GamePanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Input {
    private static Scanner scanner = new Scanner(System.in);   //takes direct input currently from the user and passes it to the program
    private static String userInput;
    private static ArrayList<String> empty = new ArrayList<>();
    private static Player player1 = Player.getInstance("default", "Player's House", "Suburb", empty);
    private static GamePanel gamePanel = new GamePanel();
    private static String itemText;

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
        }
        return newGame;
    }

    //set up listeners
    private static void setupListeners(){


        gamePanel.newGameButton.addActionListener(e -> {
            gamePanel.titleBackground.setVisible(false);
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
            gamePanel.textField.setText("Hi "+ userInput +",\n"+"It's 2 weeks into summer vacation, you and your friend Sara are extremely bored.\n" +
                    "In this town, there isn't much to do for fun.\n" +
                    "Yesterday before splitting up Sara said \"we should go to the abandoned airport tomorrow!\"\n                   **New Task**\n                EXPLORE THE AIRPORT");
        });

        gamePanel.mapButton.addActionListener(e -> {
            // This creates the map
            MapLocation map = new MapLocation();
            JFrame mapFrame = new JFrame("Map");    // create new JFrame for map
            mapFrame.add(map);  // add the map to the
            mapFrame.setSize(725, 325);
            mapFrame.setVisible(true);
            mapFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            gamePanel.mapButton.setEnabled(false);
            mapFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    gamePanel.mapButton.setEnabled(true);
                }
            });

//            JFrame frame = new JFrame("Map");
//            frame.setSize(400,400);
//            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//            ImageIcon mapIcon = new ImageIcon("Assets/map/map.png");
//            JLabel label = new JLabel(mapIcon);
//            frame.add(label);
//            frame.pack();
//            frame.setVisible(true);
//            gamePanel.mapButton.setEnabled(false);
//            frame.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    gamePanel.mapButton.setEnabled(true);
//                }
//            });
        });

        gamePanel.helpButton.addActionListener(e -> {
            JFrame frame = new JFrame("Help");
            frame.setSize(400,400);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            ImageIcon helpIcon = new ImageIcon("Assets/img/help.PNG");
            JLabel label1 = new JLabel(helpIcon);
            frame.add(label1);
            frame.pack();
            frame.setVisible(true);
            gamePanel.helpButton.setEnabled(false);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    gamePanel.helpButton.setEnabled(true);
                }
            });
        });

        gamePanel.musicButton.addActionListener(e -> {

            ImageIcon musicOn = new ImageIcon("Assets/img/on.png");
            ImageIcon musicOff = new ImageIcon("Assets/img/off.png");

            if (gamePanel.musicButton.isSelected()){
                gamePanel.musicButton.setIcon(musicOff);
                SoundFX.MUSIC1.stopPlay();
            }else {
                gamePanel.musicButton.setIcon(musicOn);
                int loop = 3;
                SoundFX.MUSIC1.loopPlay(loop);
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

        gamePanel.myCurrentTaskButton.addActionListener(e -> {

                    if (!FileManager.sceneReader("sceneOnePassed")) {
                        JOptionPane.showMessageDialog(null, "Go to Airport, remember to collect something before go there. \nGood luck!", "", JOptionPane.PLAIN_MESSAGE);
                    } else if (FileManager.sceneReader("sceneOnePassed") && !FileManager.sceneReader("sceneTwoPassed")) {
                        JOptionPane.showMessageDialog(null, "Go to Player's House", "", JOptionPane.PLAIN_MESSAGE);
                    } else if (FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed") && !FileManager.sceneReader("sceneThreePassed")) {
                        JOptionPane.showMessageDialog(null, "Go to Hay Field", "", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if (FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed") && FileManager.sceneReader("sceneThreePassed")
                            && !FileManager.sceneReader("sceneFourPassed")){
                        JOptionPane.showMessageDialog(null, "Look for Sara", "", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if (FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed") && FileManager.sceneReader("sceneThreePassed")
                            && FileManager.sceneReader("sceneFourPassed") && !FileManager.sceneReader("sceneFivePassed")){
                        JOptionPane.showMessageDialog(null, "Go to river", "", JOptionPane.PLAIN_MESSAGE);
                    }

                }

        );


        gamePanel.exploreAirportButton.addActionListener(e ->
                {
                    if (FileManager.getPlayerItems().contains("rope") && !FileManager.getPlayerItems().contains("planks")) {
                        JOptionPane.showMessageDialog(null, "It looks like you have a rope in your bag! \nYou may need something to make a ladder by combining something " +
                                "with the rope! Come back once you got the item!", "", JOptionPane.PLAIN_MESSAGE);
                    } else if (FileManager.getPlayerItems().contains("planks") && !FileManager.getPlayerItems().contains("rope")) {
                        JOptionPane.showMessageDialog(null, "It looks like you have planks in your bag! \nYou may need something to make a ladder by combining something " +
                                "with the planks! Come back once you got the item!", "", JOptionPane.PLAIN_MESSAGE);
                    } else if (FileManager.getPlayerItems().contains("rope") && FileManager.getPlayerItems().contains("planks")) {
                        gamePanel.arriveSpecialScene("scene-one");
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
                    gamePanel.taskScreen("scene-two");
                    gamePanel.taskScreenNextButtonPanel.setVisible(false);
                    gamePanel.goToHayFieldNextButtonPanel.add(gamePanel.goTOHayFieldNextButton);
                    gamePanel.con.add(gamePanel.goToHayFieldNextButtonPanel);
                    FileManager.sceneWriter(true, "sceneTwoPassed");

                }
        );

        gamePanel.goTOHayFieldNextButton.addActionListener(e->{
            gamePanel.mainTextPanel.setVisible(false);
            gamePanel.taskScreenNextButtonPanel.setVisible(false);
            gamePanel.largeTextAreaPanel.setVisible(false);
            gamePanel.locationImgPanel.setVisible(true);
            gamePanel.mainLocationDescPanel.setVisible(true);
            gamePanel.userInputPanel.setVisible(true);
            gamePanel.directionButtonPanel.setVisible(true);
            gamePanel.inventoryPanel.setVisible(true);
            gamePanel.goToHayFieldNextButtonPanel.setVisible(false);
        });

        gamePanel.exploreHayFieldButton.addActionListener(e->{
                    gamePanel.clearZoneViewPanel();
                    gamePanel.explorePlayerHouseButtonPanel.setVisible(false);
                    gamePanel.taskScreen("scene-three");
                    gamePanel.con.add(gamePanel.hayfieldNextButtonPanel);
                    gamePanel.hayfieldNextButtonPanel.setVisible(true);
                    gamePanel.hayfieldNextButtonPanel.add(gamePanel.hayfieldNextButton);
                    gamePanel.exploreHayFieldButtonPanel.setVisible(false);


                }
        );
        gamePanel.hayfieldNextButton.addActionListener(e->
                gamePanel.taskThrowRockWithEnoughInventory()
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
                    gamePanel.taskScreen("scene-four");
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

    public static void clickPic(){
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("talk");
        item1.addActionListener(e->{
            if(FileManager.getNPCsName(player1.getPlayerLocation()).size()> 0){
                // npc name
                JSONArray NPCname = FileManager.getNPCsName(player1.getPlayerLocation());

                //npc dialogue
                String dialogue = FileManager.getNPCsDialog((String) NPCname.get(0),1);
                System.out.println(dialogue);

                String a = TextToSpeech.talkNpc(dialogue);

                System.out.println(a);}
            else{
                String a = TextToSpeech.talkNpc("There was no one to talk to");
            }
        });

        popMenu.add(item1);

        gamePanel.locationImgLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    popMenu.show(gamePanel.locationImgLabel,e.getX(),e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
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
                                        JOptionPane.showMessageDialog(gamePanel, "I can't get that! There's no " + nouns.get(0) + " for me to pick up!", "", JOptionPane.PLAIN_MESSAGE);
                                    }

                                    break;
                                case "talk":
                                    JOptionPane.showMessageDialog(gamePanel, player1.talk(nouns.get(0)), "", JOptionPane.PLAIN_MESSAGE);
                                    String a = TextToSpeech.talkNpc(player1.talk(nouns.get(0)));
                                    System.out.println(a);
                                    break;
                                case "go":
                                    goDirection(nouns.get(0));
                                    break;
                                case "quit":
                                    FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
                                    System.exit(0);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(gamePanel, "I didn't understand that command. for help click help button on the top or type help.", "", JOptionPane.PLAIN_MESSAGE);
                                    break;
                            }


                        });

                    } else if (FileManager.getLocationItems(tempLocation).isEmpty()) {
                        gamePanel.seeItem.setText("");
                    }
                    //display available people in current location
                    if (!npcNames.isEmpty()) {
                        String name = null;
                        switch (npcNames.size()) {
                            case 1:
                                name = npcNames.get(0);
                        }
                        switch (npcNames.size()) {
                            case 1:
                                gamePanel.seePeople.setText("You see:\n " + name + ".");
                                break;
                        }

                    } else {
                        gamePanel.seePeople.setText("");
                    }

                }

                didMove = true;
                String currentLocation = player1.getPlayerLocation();
                String zoneImgFileName = "Assets/location-art/"+currentLocation+".png";
                ImageIcon currentLocationImg = new ImageIcon(zoneImgFileName);
                gamePanel.locationImgLabel.setIcon(currentLocationImg);
                gamePanel.locationDesc.setText(FileManager.getLocationDescription(player1.getPlayerLocation(), player1.getPlayerZone()));
                if (tempLocation.equalsIgnoreCase("player's house") && FileManager.sceneReader("sceneOnePassed") && !FileManager.sceneReader("sceneTwoPassed")) {
                    gamePanel.directionButtonPanel.setVisible(false);
                    gamePanel.con.add(gamePanel.explorePlayerHouseButtonPanel);
                    gamePanel.explorePlayerHouseButtonPanel.add(gamePanel.explorePlayerHouseButton);

                }
                if (tempLocation.equalsIgnoreCase("hay field") && FileManager.sceneReader("sceneOnePassed") &&
                        FileManager.sceneReader("sceneTwoPassed") && !FileManager.sceneReader("sceneThreePassed")) {
                    gamePanel.directionButtonPanel.setVisible(false);
                    gamePanel.exploreHayFieldButtonPanel.setVisible(true);
                    gamePanel.con.add(gamePanel.exploreHayFieldButtonPanel);
                    gamePanel.exploreHayFieldButtonPanel.add(gamePanel.exploreHayFieldButton);

                }
                if (tempLocation.equalsIgnoreCase("old house south") && FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed")
                        && FileManager.sceneReader("sceneThreePassed")&&!FileManager.sceneReader("sceneFourPassed")) {
                    gamePanel.directionButtonPanel.setVisible(false);
                    gamePanel.exploreOldHouseSouthButtonPanel.setVisible(true);
                    gamePanel.con.add(gamePanel.exploreOldHouseSouthButtonPanel);
                    gamePanel.exploreOldHouseSouthButtonPanel.add(gamePanel.exploreOldHouseSouthButton);



                }
                if (tempLocation.equalsIgnoreCase("river")&& FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed")
                        && FileManager.sceneReader("sceneThreePassed")&&FileManager.sceneReader("sceneFourPassed")&&!FileManager.sceneReader("sceneFivePassed")) {
                    gamePanel.directionButtonPanel.setVisible(false);
                    gamePanel.exploreRiverButtonPanel.setVisible(true);
                    gamePanel.con.add(gamePanel.exploreRiverButtonPanel);
                    gamePanel.exploreRiverButtonPanel.add(gamePanel.exploreRiverButton);

                }
            }

        }

        if (!didMove) {
            JOptionPane.showMessageDialog(null,"you were unable to move " + direction + ".","",JOptionPane.PLAIN_MESSAGE);
        }
        FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());

    }


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
