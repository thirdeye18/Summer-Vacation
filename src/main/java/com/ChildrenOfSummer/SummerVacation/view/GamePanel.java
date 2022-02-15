package com.ChildrenOfSummer.SummerVacation.view;

import com.ChildrenOfSummer.SummerVacation.FileManager;
import com.ChildrenOfSummer.SummerVacation.Player;
import com.ChildrenOfSummer.SummerVacation.Util.Directions;
import com.ChildrenOfSummer.SummerVacation.Util.JsonHandler;
import com.ChildrenOfSummer.SummerVacation.Util.SoundFX;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;+


public class GamePanel extends JFrame {

    public JTextField userName = new JTextField("", 20);
    public JTextField userInput = new JTextField(10);
    private JTextArea textArea = new JTextArea(20, 20);
    private JTextArea askUserName = new JTextArea();
    public JTextArea seeItem = new JTextArea();
    private JTextArea seePeople = new JTextArea();
    public JTextArea textField = new JTextArea();
    public JTextArea inventoryTextField = new JTextArea();
    public JTextArea locationDesc = new JTextArea();
    public JTextArea askUserInput = new JTextArea();
    public JTextArea largeTextArea = new JTextArea("",22,80);

    public JButton newGameButton,loadGameButton, quitGameButton,playerPageEnterGameButton, introScreenNextButton,userInputEnterButton,
            mapButton,helpButton,northButton,southButton,eastButton,westButton,dropButton,useButton,
            exploreAirportButton, arriveSpecialSceneNextButton,taskScreenNextButton,explorePlayerHouseButton,exploreHayFieldButton,
            hayfieldNextButton;
    private Container con;
    public JPanel titleNamePanel, newGameButtonPanel,askForNamePanel,playerPageFooterPanel, introScreenEnterGameButtonPanel,
            mainTextPanel,mainLocationDescPanel,locationImgPanel,userInputPanel,headerContentPanel,directionButtonPanel,
            inventoryPanel, largeTextAreaPanel,exploreButtonPanel,taskScreenNextButtonPanel,explorePlayerHouseButtonPanel,
            exploreHayFieldButtonPanel,hayfieldNextButtonPanel;
    public JLabel titleNameLabel,locationImgLabel;
    public JToggleButton musicButton;
    public BorderLayout setLayout;
    public DefaultListModel inventoryListModel;
    public JList inventoryList;
    public JScrollPane scroll;
    //from Input class
    private static String ANSWER;
    private static ArrayList<String> empty = new ArrayList<>();
    private static Player player1 = Player.getInstance("default", "Player's House", "Suburb", empty);
    String locationDescJson = FileManager.getLocationDescription(player1.getPlayerLocation(), player1.getPlayerZone());



    public GamePanel() {
        super("Summer Vacation");
        setUpMainMenu();
        setupListeners();

    }

    public void setUpMainMenu() {
        setFrameConfigs();
        con = getContentPane();

        setAllButtons();
        setAllPanels();
        // ---- LABELS ADDED TO PANELS ----
        titleNameLabel = new JLabel("SUMMER VACATION");

        titleNamePanel.add(titleNameLabel);
        newGameButtonPanel.add(newGameButton);
        newGameButtonPanel.add(loadGameButton);
        newGameButtonPanel.add(quitGameButton);


        con.add(titleNamePanel);
        con.add(newGameButtonPanel);
        con.add(askForNamePanel);
        con.add(playerPageFooterPanel);
        con.add(introScreenEnterGameButtonPanel);

        setVisible(true);
    }


    // default frame settings
    private void setFrameConfigs() {
        setSize(800, 600);
        setResizable(false);        // enable the resize of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setVisible(true);
    }


    //set all panels
    private void setAllPanels() {
        titleNamePanel = createJPanel(100,100,600,150,Color.blue, true);
        newGameButtonPanel = createJPanel(300,400,200,150,Color.yellow,true);
        askForNamePanel = createJPanel(100,100,600,250,Color.blue,true);
        playerPageFooterPanel = createJPanel(500,500,100,30,Color.red,true);
        introScreenEnterGameButtonPanel = createJPanel(600,400,200,100,Color.yellow,true);
        mainTextPanel = createJPanel(20,60,740,430,Color.yellow,true);
        locationImgPanel = createJPanel(20,30,540,300,Color.blue,true);
        mainLocationDescPanel = createJPanel(20,330,540,140,Color.red,true);
        userInputPanel = createJPanel(20,480,300,60,Color.green,true);
        headerContentPanel = createJPanel(0,0,800,30, Color.yellow,true);
        directionButtonPanel = createJPanel(630,300,100,150,Color.yellow,true);
        inventoryPanel = createJPanel(620,50,120,250,Color.yellow,true);
        largeTextAreaPanel = createJPanel(20,60,740,430,Color.white,true);
        exploreButtonPanel = createJPanel(620,500,100,30,Color.white,true);
        explorePlayerHouseButtonPanel = createJPanel(620,500,100,30,Color.white,true);
        exploreHayFieldButtonPanel = createJPanel(620,500,100,30,Color.white,true);
        taskScreenNextButtonPanel = createJPanel(600,400,200,100,Color.yellow,true);
        hayfieldNextButtonPanel = createJPanel(600,400,200,100,Color.yellow,true);
    }

    private void setAllButtons() {
        newGameButton = createJButton("New Game",150,50,false,Color.white,Color.black);
        loadGameButton = createJButton("Load Game",150,50,false,Color.white,Color.black);
        quitGameButton = createJButton("Quit Game",150,50,false,Color.white,Color.black);
        playerPageEnterGameButton = createJButton("ENTER GAME",150,50,false, Color.white,Color.black);
        introScreenNextButton = createJButton("NEXT",150,50,false,Color.white,Color.black);
        userInputEnterButton = createJButton("ENTER",150,50,false,Color.white,Color.black);
        mapButton = createJButton("MAP",100,30,false,Color.white,Color.black);
        helpButton = createJButton("HELP",100,30,false,Color.white,Color.black);
        northButton = createJButton("Go North",100,20,false,Color.white,Color.black);
        southButton = createJButton("Go South",100,20,false,Color.white,Color.black);
        westButton = createJButton("Go West",100,20,false,Color.white,Color.black);
        eastButton = createJButton("Go East",100,20,false,Color.white,Color.black);
        useButton = createJButton("use item",100,20,false,Color.white,Color.black);
        dropButton = createJButton("drop item",100,20,false,Color.white,Color.black);
        exploreAirportButton = createJButton("GO TO AIRPORT",100,20,false,Color.red,Color.white);
        explorePlayerHouseButton = createJButton("GO TO Player's House",100,20,false,Color.red,Color.white);
        exploreHayFieldButton = createJButton("GO TO Hay Field",100,20,false,Color.red,Color.white);
        arriveSpecialSceneNextButton = createJButton("NEXT",100,30,false,Color.white,Color.black);
        taskScreenNextButton = createJButton("GO TASK",150,50,false,Color.white,Color.black);
        hayfieldNextButton = createJButton("GO hay field",150,50,false,Color.white,Color.black);
    }
    public void createGameScreen() {
        titleNamePanel.setVisible(true);
        newGameButtonPanel.setVisible(true);
        askForNamePanel.setVisible(false);
        playerPageFooterPanel.setVisible(false);
        introScreenEnterGameButtonPanel.setVisible(false);
    }

    public void playerNameScreen() {
        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel.setVisible(true);
        playerPageFooterPanel.setVisible(true);
        introScreenEnterGameButtonPanel.setVisible(true);

        askUserName.setText("Please enter your name: ");
        askUserName.setBackground(Color.black);
        askUserName.setForeground(Color.white);
        askUserName.setEditable(false);
        askForNamePanel.add(askUserName);
        askForNamePanel.add(userName);



        musicButton = new JToggleButton("music on/off");
        playerPageFooterPanel.add(musicButton);
        introScreenEnterGameButtonPanel.add(playerPageEnterGameButton);


        // Zoe notes: task --add function to playerPageEnterGameButton,
        // need to add introduction.txt to task1Screen mainTextPanel.
        // use writeToTextArea()
    }

    //set up listeners
    private void setupListeners(){

        newGameButton.addActionListener(e -> {
            playerNameScreen();
            player1.setPlayerInventory(empty);
            player1.setPlayerName("default");
            player1.setPlayerLocation("Player's House");
            player1.setPlayerZone("Suburb");
        });


        quitGameButton.addActionListener(e->System.exit(0));

        playerPageEnterGameButton.addActionListener(e-> {
           introScreen();
           ANSWER = userName.getText();
           player1.setPlayerName(ANSWER);
           FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
           textField.setText("Hi "+ ANSWER+"\n"+FileManager.txtFileToString("introduction.txt"));
        });

        mapButton.addActionListener(e -> {
            JFrame frame = new JFrame("Map");
            frame.setSize(400,400);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            ImageIcon mapIcon = new ImageIcon("Assets/img/map.PNG");
            JLabel label = new JLabel(mapIcon);
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        });
        helpButton.addActionListener(e -> {
            JFrame frame = new JFrame("Map");
            frame.setSize(400,400);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            ImageIcon helpIcon = new ImageIcon("Assets/img/help.PNG");
            JLabel label1 = new JLabel(helpIcon);
            frame.add(label1);
            frame.pack();
            frame.setVisible(true);
        });

        northButton.addActionListener(e -> {
            goDirection("north");
        });
        southButton.addActionListener(e -> {
            goDirection("south");
        });
        eastButton.addActionListener(e -> {
            goDirection("east");
        });
        westButton.addActionListener(e -> {
            goDirection("west");
        });

        dropButton.addActionListener(e -> {
            ArrayList<String> locationList = FileManager.getLocationItems(player1.getPlayerLocation());
            ArrayList<String> playerList = FileManager.getPlayerItems();
                int index = inventoryList.getSelectedIndex();
                if (index>=0) {
                    String droppedItem = (String) inventoryList.getSelectedValue();
                    locationList.add(droppedItem);
                    playerList.remove(droppedItem);
                    FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                    FileManager.savePlayerItems(playerList);
                    player1.setPlayerInventory(playerList);
                    inventoryListModel.removeElementAt(index);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Nothing selected, please select one item to drop", "", JOptionPane.PLAIN_MESSAGE);
                }
        });

        useButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"Nothing happens...", "", JOptionPane.PLAIN_MESSAGE);
        });

        loadGameButton.addActionListener(e -> {
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



        userInputEnterButton.addActionListener(e->{

            ArrayList<String> locationList = FileManager.getLocationItems(player1.getPlayerLocation());
            ArrayList<String> playerList = FileManager.getPlayerItems();
            ANSWER = userInput.getText();
            String[] answerWords = ANSWER.split(" ");
            String verb = answerWords[0].toLowerCase();
            String noun2 = answerWords[answerWords.length - 1].toLowerCase();
            switch (verb) {
                case "map":
                    JFrame frame = new JFrame("Map");
                    frame.setSize(400, 400);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    ImageIcon mapIcon = new ImageIcon("Assets/img/map.PNG");
                    JLabel label = new JLabel(mapIcon);
                    frame.add(label);
                    frame.pack();
                    frame.setVisible(true);
                    break;
                case "inventory":
                    JOptionPane.showMessageDialog(null, "Your inventory has: " + playerList, "", JOptionPane.PLAIN_MESSAGE);
                    break;
                case "get":
                    if (locationList.contains(noun2)) {

                        locationList.remove(noun2);
                        playerList.add(noun2);
                        FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                        FileManager.savePlayerItems(playerList);
                        player1.setPlayerInventory(playerList);
                        inventoryListModel.addElement(noun2);

                        JOptionPane.showMessageDialog(null, noun2 + " has been added to your inventory.", "", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        System.out.println(locationList);
                        JOptionPane.showMessageDialog(null, "I can't get that! There's no " + noun2 + " for me to pick up!", "", JOptionPane.PLAIN_MESSAGE);

                    }

                    break;

                case "drop":
                    if (playerList.contains(noun2)) {
                        locationList.add(noun2);
                        playerList.remove(noun2);
                        FileManager.updateLocationItems(player1.getPlayerLocation(), locationList);
                        FileManager.savePlayerItems(playerList);
                        player1.setPlayerInventory(playerList);
                        inventoryListModel.removeElement(noun2);
                    } else {
                        JOptionPane.showMessageDialog(null,"Sorry, I can't drop what I don't have.", "", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;
                case "use":
                    JOptionPane.showMessageDialog(null,"Nothing happens...", "", JOptionPane.PLAIN_MESSAGE);
                    break;
                case "talk":
                    JOptionPane.showMessageDialog(null, player1.talk(noun2), "", JOptionPane.PLAIN_MESSAGE);
                    break;

                case "help":
                    frame = new JFrame("Map");
                    frame.setSize(400, 400);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    ImageIcon helpIcon = new ImageIcon("Assets/img/help.PNG");
                    JLabel label1 = new JLabel(helpIcon);
                    frame.add(label1);
                    frame.pack();
                    frame.setVisible(true);
                    break;
                case "music":
                    switch (noun2) {
                        case "on":
                            int loop = 3;
                            SoundFX.MUSIC1.loopPlay(loop);
                            JOptionPane.showMessageDialog(null, "Back ground music turned on", "", JOptionPane.PLAIN_MESSAGE);
                            break;
                        case "off":
                            SoundFX.MUSIC1.stopPlay();
                            JOptionPane.showMessageDialog(null, "Back ground music turned off", "", JOptionPane.PLAIN_MESSAGE);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Not a valid response\n [on] [off]", "", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;
                case "go":
                    goDirection(noun2);
                    break;
                case "quit":
                    FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "I didn't understand that command. for help click help button on the top or type help.", "", JOptionPane.PLAIN_MESSAGE);
                  }


        });
        exploreAirportButton.addActionListener(e ->
        {
            arriveSpecialScene("scene-one.txt");
        });

        arriveSpecialSceneNextButton.addActionListener(e ->
        {
                if (FileManager.getPlayerItems().contains("rope") && FileManager.getPlayerItems().contains("planks")) {
                    taskEscapeWithEnoughInventory();
                } else {
                    JOptionPane.showMessageDialog(null,"With no items to help you, you and your friends are caught by security! You're grounded!\nGame Over!","",JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
                }
        });

        taskScreenNextButton.addActionListener(e->{
            mainTextPanel.setVisible(false);
            taskScreenNextButtonPanel.setVisible(false);
            largeTextAreaPanel.setVisible(false);
           locationImgPanel.setVisible(true);
           mainLocationDescPanel.setVisible(true);
           userInputPanel.setVisible(true);
           directionButtonPanel.setVisible(true);
           inventoryPanel.setVisible(true);

                }



        );

        explorePlayerHouseButton.addActionListener(e->{
            clearZoneViewPanel();
            explorePlayerHouseButtonPanel.setVisible(false);
            taskScreen("scene-two.txt");
            taskScreenNextButtonPanel.setVisible(true);
            taskScreenNextButtonPanel.add(taskScreenNextButton);
            con.add(taskScreenNextButtonPanel);
            FileManager.sceneWriter(true, "sceneTwoPassed");
            JOptionPane.showMessageDialog(null,"You arrived and completed task", "", JOptionPane.PLAIN_MESSAGE);
                }

        );

        exploreHayFieldButton.addActionListener(e->{
                    clearZoneViewPanel();
                    explorePlayerHouseButtonPanel.setVisible(false);
                    taskScreen("scene-three.txt");
                    con.add(hayfieldNextButtonPanel);
                    hayfieldNextButtonPanel.setVisible(true);
                    hayfieldNextButtonPanel.add(hayfieldNextButton);
                    exploreHayFieldButtonPanel.setVisible(false);
                    FileManager.sceneWriter(true, "sceneThreePassed");
                    JOptionPane.showMessageDialog(null,"You arrived and completed task", "", JOptionPane.PLAIN_MESSAGE);
                }

        );
        hayfieldNextButton.addActionListener(e->{
                    taskThrowRockWithEnoughInventory();
                }

        );
    }

    public void introScreen() {
        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel.setVisible(false);
        playerPageEnterGameButton.setVisible(false);
        textField.setEditable(false);
        mainTextPanel.add(textField);
        con.add(mainTextPanel);

        mainTextPanel.add(textField);
        introScreenEnterGameButtonPanel.add(introScreenNextButton);
        introScreenNextButton.addActionListener(e -> zoneView());
    }

    public void zoneView() {

        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel.setVisible(false);
        playerPageEnterGameButton.setVisible(false);
        introScreenEnterGameButtonPanel.setVisible(false);
        mainTextPanel.setVisible(false);


        //Location description panel.
        con.add(mainLocationDescPanel);
        exploreButtonPanel.add(exploreAirportButton);
        con.add(exploreButtonPanel);
        // text description of current location

        // add a picture
        locationImgLabel = new JLabel();
        locationImgLabel.setBounds(20,120,540,300);
        locationImgLabel.setBackground(Color.BLUE);
        String currentZone = player1.getPlayerZone().toLowerCase();
        String zoneImgFileName = "Assets/zone-png/"+currentZone+".jpg";
        ImageIcon currentZoneImg = new ImageIcon(zoneImgFileName);
        locationImgLabel.setIcon(currentZoneImg);
        locationDesc.setForeground(Color.white);
        locationDesc.setBackground(Color.blue);
        locationDesc.setEditable(false);
        seeItem.setBackground(Color.green);
        seeItem.setForeground(Color.white);
        seeItem.setEditable(false);





        seePeople.setBackground(Color.pink);
        seePeople.setForeground(Color.white);
        seePeople.setEditable(false);
        con.add(locationImgPanel);
        locationImgPanel.add(locationImgLabel);



        //get description from Locations.JSON
        locationDesc.append(locationDescJson);

        //display available items for current location
        if (!FileManager.getLocationItems(player1.getPlayerLocation()).isEmpty()) {
            String seeItems="You see the following items lying around: ";
            String result = String.join(",", FileManager.getLocationItems(player1.getPlayerLocation()));
            String itemText = seeItems + "\n" + result;
            seeItem.setText(itemText+"\n");

        }
        else if(FileManager.getLocationItems(player1.getPlayerLocation()).isEmpty()){
            seeItem.setText("");
        }
        //display available people in current location
        JSONArray NPCname= FileManager.getNPCsName(player1.getPlayerLocation());
        ArrayList<String> npcNames = (ArrayList<String>) NPCname;

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
                    seePeople.setText("You see " + nameThree + ", " + nameTwo + ", and " + name + ".");
                    break;
                case 2:
                    seePeople.setText("You see " + name + " and " + nameTwo + ".");
                    break;
                case 1:
                    seePeople.setText("You see "+name+".");
                    break;
            }
        }
        else if(npcNames.isEmpty()){
            seePeople.setText("");
        }



        mainLocationDescPanel.add(locationDesc);  // add "you are in xxxx, to the north is xxxx..."
        mainLocationDescPanel.add(seeItem);
        mainLocationDescPanel.add(seePeople);
        // UserInput panel, includes: text-What would you like to do? & textfield which takes user input & a ENTER button
        userInputPanel.setLayout(new GridLayout(3,1));
        // "What would you like to do?"
        askUserInput = new JTextArea("What would you like to do?");
        askUserInput.setEnabled(false);
        askUserInput.setEditable(false);
        askUserInput.setBackground(Color.black);
        askUserInput.setForeground(Color.white);
        con.add(userInputPanel);

        //user input textfield
        userInput.setBackground(Color.black);
        userInput.setForeground(Color.white);

        // add all three components to userInputPanel
        userInputPanel.add(askUserInput);
        userInputPanel.add(userInput);
        userInputPanel.add(userInputEnterButton);


        // Headers - include map/help buttons
        con.add(headerContentPanel);

        // map button
        headerContentPanel.add(mapButton);


        //help button
        headerContentPanel.add(helpButton);

        // Direction buttons -North/South/West/East
        con.add(directionButtonPanel);

        directionButtonPanel.add(northButton);
        directionButtonPanel.add(southButton);
        directionButtonPanel.add(westButton);
        directionButtonPanel.add(eastButton);




        // inventory panel
        con.add(inventoryPanel);

        inventoryTextField.setText("**Your inventory**");
        inventoryTextField.setBackground(Color.yellow);
        inventoryPanel.add(inventoryTextField);

        inventoryListModel = new DefaultListModel();
        // for iteration 1 demo purpose

        inventoryList = new JList(inventoryListModel);

        inventoryList.setFixedCellWidth(80);

        JScrollPane pane = new JScrollPane(inventoryList);

        inventoryPanel.add(pane, BorderLayout.NORTH);
        inventoryPanel.add(useButton, BorderLayout.WEST);
        inventoryPanel.add(dropButton, BorderLayout.EAST);

    }

    // arrive to special scene. read txt
    public void arriveSpecialScene(String fileName) {
        mainLocationDescPanel.setVisible(false);
        userInputPanel.setVisible(false);
        directionButtonPanel.setVisible(false);
        inventoryPanel.setVisible(false);
        locationImgPanel.setVisible(false);
        exploreButtonPanel.setVisible(false);
       // String text =FileManager.txtFileToString("scene-one.txt");
        String text = FileManager.txtFileToString(fileName);
        largeTextArea.setText(text);
        largeTextArea.setEditable(false);


        largeTextAreaPanel.add(largeTextArea);
        largeTextArea.setLineWrap(true);
        scroll = new JScrollPane(largeTextArea);
        largeTextArea.setBounds(20,60,740,380);
        largeTextAreaPanel.add(scroll);
        largeTextAreaPanel.add(arriveSpecialSceneNextButton);
        con.add(largeTextAreaPanel);
    }

    public void failedPassOrFinalWin(String fileName) {
        mainLocationDescPanel.setVisible(false);
        userInputPanel.setVisible(false);
        directionButtonPanel.setVisible(false);
        inventoryPanel.setVisible(false);
        // String text =FileManager.txtFileToString("scene-one.txt");
        String text = FileManager.txtFileToString(fileName);
        largeTextArea.setText(text);


        largeTextAreaPanel.add(largeTextArea);
        largeTextArea.setLineWrap(true);
        scroll = new JScrollPane(largeTextArea);
        largeTextArea.setBounds(20,60,740,380);
        largeTextAreaPanel.add(scroll);
        largeTextAreaPanel.add(quitGameButton);
        con.add(largeTextAreaPanel);
    }
    public void taskEscapeWithEnoughInventory() {
        mainLocationDescPanel.setVisible(false);
        userInputPanel.setVisible(false);
        directionButtonPanel.setVisible(false);
        inventoryPanel.setVisible(false);
        // String text =FileManager.txtFileToString("scene-one.txt");

       int reply1 = JOptionPane.showConfirmDialog(null, "You noticed that your rope and planks could be combined!\n" +
                "You can create a ladder to get out!\n" +
                "Do you wish to combine the items to get out?", "", JOptionPane.YES_NO_OPTION);
       if (reply1 == JOptionPane.YES_OPTION){
           boolean sceneOnePass = FileManager.sceneReader("sceneOnePassed");
           ArrayList<String> playerList = FileManager.getPlayerItems();
           ArrayList<String> locationList = FileManager.getLocationItems(player1.getPlayerLocation());
           sceneOnePass = true;
           player1.getPlayerInventory();
           playerList.remove("rope");
           playerList.remove("planks");
           playerList.add("ladder");
            inventoryListModel.removeElement("rope");
            inventoryListModel.removeElement("planks");
            inventoryListModel.addElement("ladder");

           player1.setPlayerInventory(playerList);
           FileManager.savePlayerItems(playerList);
           FileManager.sceneWriter(true, "sceneOnePassed");
           JOptionPane.showMessageDialog(null,"You've finished the first scene. You have gotten out of the airport!","",JOptionPane.PLAIN_MESSAGE);
           taskScreen("scene-one-end.txt");

       }
       else{
           JOptionPane.showMessageDialog(null,"you lost the game","",JOptionPane.PLAIN_MESSAGE);
           System.exit(0);
       }

        largeTextAreaPanel.add(largeTextArea);
        con.add(largeTextAreaPanel);
    }


    public void taskThrowRockWithEnoughInventory() {
        mainLocationDescPanel.setVisible(false);
        userInputPanel.setVisible(false);
        directionButtonPanel.setVisible(false);
        inventoryPanel.setVisible(false);
        // String text =FileManager.txtFileToString("scene-one.txt");

        int reply1 = JOptionPane.showConfirmDialog(null, "You can throw a rock to escape. Do you want to?", "", JOptionPane.YES_NO_OPTION);
        if (reply1 == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,"You've escaped from the farmer.","",JOptionPane.PLAIN_MESSAGE);
            taskScreen("scene-three-end.txt");
            hayfieldNextButtonPanel.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(null,"You got caught! Game Over.","",JOptionPane.PLAIN_MESSAGE);
        }


        largeTextAreaPanel.add(largeTextArea);
        con.add(largeTextAreaPanel);
    }
    public void taskPaddleRiverWithEnoughInventory() {
        mainLocationDescPanel.setVisible(false);
        userInputPanel.setVisible(false);
        directionButtonPanel.setVisible(false);
        inventoryPanel.setVisible(false);
        // String text =FileManager.txtFileToString("scene-one.txt");

        Object[] paddleOptions = { "paddle left", "paddle right" };
        int reply1 = JOptionPane.showOptionDialog(null, "Rapids rush up to meet you in the middle of the river!\nWhich way will you paddle to avoid them?",
                "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, paddleOptions, paddleOptions[0]);
        if (reply1 == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,"You paddle left. A tree branch in the river snags your raft! It takes some damage.","",JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,"You paddle right. A massive boulder stops your progress... \nAfter dislodging your raft you are free.","",JOptionPane.PLAIN_MESSAGE);
        }

        Object[] innerOrCenter = { "hug the inner bank", "center the raft" };
        int reply2 = JOptionPane.showOptionDialog(null, "The river curves left\nYou need to choose to hug the inner bank of center the raft in the river.",
                "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, innerOrCenter, innerOrCenter[0]);
        if (reply2 == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,"A rock in the shallow inner bank scrapes your raft! ","",JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,"The raft hits no obstacles as you glide around the bend.","",JOptionPane.PLAIN_MESSAGE);
        }

        Object[] duckOrPaddle = { "duck under branches", "use paddle on branches" };
        int reply3 = JOptionPane.showOptionDialog(null, "Ahead, thorny branches almost cover the water.\nThey will snag you unless you find a way to avoid them!",
                "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, duckOrPaddle, duckOrPaddle[0]);
        if (reply3 == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,"The branches puncture holes in your raft!","",JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,"You push the thorny branches out of the way of your raft.","",JOptionPane.PLAIN_MESSAGE);
        }


        largeTextAreaPanel.add(largeTextArea);
        con.add(largeTextAreaPanel);
    }


    public void clearZoneViewPanel() {
        locationImgPanel.setVisible(false);
        mainLocationDescPanel.setVisible(false);
        inventoryPanel.setVisible(false);
        directionButtonPanel.setVisible(false);
        userInputPanel.setVisible(false);
    }




    private JButton createJButton(String title, int width, int height, boolean focusable, Color foreground, Color background) {
        JButton product = new JButton(title);
        product.setPreferredSize(new Dimension(width, height));
        product.setFocusable(focusable);
        product.setForeground(foreground);
        product.setBackground(background);
        return product;
    }

    private JPanel createJPanel(int x, int y, int width, int height, Color background, boolean visible) {
        JPanel product = new JPanel();
        product.setBounds(x, y, width, height);
        product.setBackground(background);
        product.setVisible(visible);
        return product;
    }

    public void goDirection(String direction){
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
                        String seeItems = "You see the following items lying around: ";
                        String result = String.join(",", FileManager.getLocationItems(tempLocation));
                        String itemText = seeItems + "\n" + result;
                        seeItem.setText(itemText + "\n");

                    } else if (FileManager.getLocationItems(tempLocation).isEmpty()) {
                        seeItem.setText("");
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
                                seePeople.setText("You see " + nameThree + ", " + nameTwo + ", and " + name + ".");
                                break;
                            case 2:
                                seePeople.setText("You see " + name + " and " + nameTwo + ".");
                                break;
                            case 1:
                                seePeople.setText("You see " + name + ".");
                                break;
                        }

                    } else {
                        seePeople.setText("");
                    }

                }

                didMove = true;
                String currentZone = player1.getPlayerZone().toLowerCase();
                String zoneImgFileName = "Assets/zone-png/" + currentZone + ".jpg";
                ImageIcon currentZoneImg = new ImageIcon(zoneImgFileName);
                locationImgLabel.setIcon(currentZoneImg);
                locationDesc.setText(FileManager.getLocationDescription(player1.getPlayerLocation(), player1.getPlayerZone()));
                if ((tempLocation.equalsIgnoreCase("player's house")) && FileManager.sceneReader("sceneOnePassed") && !FileManager.sceneReader("sceneTwoPassed")) {
                    directionButtonPanel.setVisible(false);
                    con.add(explorePlayerHouseButtonPanel);
                    explorePlayerHouseButtonPanel.add(explorePlayerHouseButton);

                }
                if ((tempLocation.equalsIgnoreCase("hay field")) && FileManager.sceneReader("sceneOnePassed") && FileManager.sceneReader("sceneTwoPassed") && !FileManager.sceneReader("sceneThreePassed")) {
                    directionButtonPanel.setVisible(false);
                    exploreHayFieldButtonPanel.setVisible(true);
                    con.add(exploreHayFieldButtonPanel);
                    exploreHayFieldButtonPanel.add(exploreHayFieldButton);

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
                    seeItem.setText(itemText+"\n");
                }
                else if(FileManager.getLocationItems(tempLocation).isEmpty()){
                    seeItem.setText("");
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
                            seePeople.setText("You see " + nameThree + ", " + nameTwo + ", and " + name + ".");
                            break;
                        case 2:
                            seePeople.setText("You see " + name + " and " + nameTwo + ".");
                            break;
                        case 1:
                            seePeople.setText("You see "+name+".");
                            break;
                    }
                }
                else{
                    seePeople.setText("");
                }
                didMove = true;
            }
        }

        if (!didMove) {
            JOptionPane.showMessageDialog(null,"you were unable to move " + direction + ".","",JOptionPane.PLAIN_MESSAGE);
        }
        FileManager.saveGame(player1.getPlayerName(), player1.getPlayerLocation(), player1.getPlayerZone(), player1.getPlayerInventory());

    }
    public void exploreSpecialSceneClearedPanel(){
        exploreButtonPanel.add(exploreAirportButton);
        con.add(exploreButtonPanel);
    }

    public void taskScreen(String taskFileName){
        largeTextAreaPanel.setVisible(false);
        con.add(mainTextPanel);
        con.add(taskScreenNextButtonPanel);

        mainTextPanel.setVisible(true);

        taskScreenNextButtonPanel.add(taskScreenNextButton);
        textField.setText(FileManager.txtFileToString(taskFileName));
        mainTextPanel.add(textField);

    }



}
