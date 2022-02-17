package com.ChildrenOfSummer.SummerVacation.view;

import com.ChildrenOfSummer.SummerVacation.FileManager;
import com.ChildrenOfSummer.SummerVacation.Player;
import org.json.simple.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GamePanel extends JFrame {

    public JTextField userName = new JTextField("", 30);
    public JTextField userInput = new JTextField(10);
    private JTextArea textArea = new JTextArea(20, 20);
    private JTextArea askUserName = new JTextArea();
    public JTextArea seeItem = new JTextArea();
    public JTextArea seePeople = new JTextArea();
    public static JTextArea textField = new JTextArea();
    public JTextArea inventoryTextField = new JTextArea();
    public JTextArea locationDesc = new JTextArea();
    public JTextArea askUserInput = new JTextArea();
    public static JTextArea largeTextArea = new JTextArea("",22,80);

    public JButton newGameButton;
    public JButton loadGameButton;
    public JButton quitGameButton;
    public JButton playerPageEnterGameButton;
    public JButton introScreenNextButton;
    public JButton userInputEnterButton;
    public JButton mapButton;
    public JButton helpButton;
    public JButton northButton = new JButton("", new ImageIcon("Assets/img/N.png"));
    public JButton southButton = new JButton("", new ImageIcon("Assets/img/S.png"));
    public JButton eastButton = new JButton("", new ImageIcon("Assets/img/E.png"));
    public JButton westButton = new JButton("", new ImageIcon("Assets/img/W.png"));
    public JButton dropButton;
    public JButton useButton;
    public JButton exploreAirportButton;
    public JButton arriveSpecialSceneNextButton;
    public static JButton taskScreenNextButton;
    public JButton explorePlayerHouseButton;
    public JButton exploreHayFieldButton;
    public JButton hayfieldNextButton;
    public JButton findSaraButton;
    public JButton findSuppliesButton;
    public JButton exploreOldHouseSouthButton;
    public JButton exploreRiverButton;
    public static JButton goToIslandButton;
    public static Container con;
    public JPanel titleNamePanel;
    public JPanel newGameButtonPanel;
    public JPanel askForNamePanel;
    public JPanel playerPageFooterPanel;
    public JPanel introScreenEnterGameButtonPanel;
    public static JPanel mainTextPanel;
    public static JPanel mainLocationDescPanel;
    public static JPanel locationImgPanel;
    public static JPanel userInputPanel;
    public JPanel headerContentPanel;
    public static JPanel directionButtonPanel;
    public static JPanel inventoryPanel;
    public static JPanel largeTextAreaPanel;
    public JPanel exploreButtonPanel;
    public static JPanel taskScreenNextButtonPanel;
    public JPanel explorePlayerHouseButtonPanel;
    public JPanel exploreHayFieldButtonPanel;
    public JPanel hayfieldNextButtonPanel;
    public JPanel findSaraButtonPanel;
    public static JPanel findSuppliesButtonPanel;
    public static JPanel exploreOldHouseSouthButtonPanel;
    public static JPanel exploreRiverButtonPanel;
    public static JPanel goToIslandButtonPanel;
    public JLabel titleNameLabel,locationImgLabel;
    public JToggleButton musicButton;
    public BorderLayout setLayout;
    public DefaultListModel inventoryListModel;
    public JList inventoryList;
    public JScrollPane scroll;
    public JLabel titleBackground,gameBackground;
    //from Input class
    public static String ANSWER;
    private static ArrayList<String> empty = new ArrayList<>();
    private static Player player1 = Player.getInstance("default", "Player's House", "Suburb", empty);
    String locationDescJson = FileManager.getLocationDescription(player1.getPlayerLocation(), player1.getPlayerZone());



    public GamePanel() {
        super("Summer Vacation");
        setUpMainMenu();

    }

    public void setUpMainMenu() {
        setFrameConfigs();
        con = getContentPane();


        setAllButtons();
        setAllPanels();
        // ---- LABELS ADDED TO PANELS ----

        ImageIcon background=new ImageIcon("Assets/img/bg.jpeg");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(800,600,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        titleBackground = new JLabel(background);
        titleBackground.setLayout(null);
        titleBackground.setBounds(0,0,800,600);


        titleBackground.add(newGameButtonPanel);
        newGameButtonPanel.add(newGameButton);
        newGameButtonPanel.add(quitGameButton);

        con.add(titleBackground);
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
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Assets/img/bg2.png")));

        setLayout(null);
        setVisible(true);
    }


    //set all panels
    private void setAllPanels() {
        titleNamePanel = createJPanel(100,100,600,150,Color.black, true);
        newGameButtonPanel = createJPanel(300,400,200,150,new Color(0,0,0,0),true);
        askForNamePanel = createJPanel(100,100,600,120,Color.black,true);
        playerPageFooterPanel = createJPanel(500,500,100,30,Color.black,true);
        introScreenEnterGameButtonPanel = createJPanel(600,420,160,50,new Color(0,0,0,0),true);
        mainTextPanel = createJPanel(20,60,740,350,Color.black,true);
        locationImgPanel = createJPanel(20,30,540,300,Color.black,true);
        mainLocationDescPanel = createJPanel(20,330,540,140,Color.black,true);
        userInputPanel = createJPanel(20,480,300,60,Color.black,true);
        headerContentPanel = createJPanel(0,0,800,30, Color.black,true);
        directionButtonPanel = createJPanel(600,290,144,144,Color.black,true);
        inventoryPanel = createJPanel(620,50,120,250,Color.black,true);
        largeTextAreaPanel = createJPanel(20,60,740,420,Color.black,true);
        exploreButtonPanel = createJPanel(600,450,180,40,Color.black,true);
        explorePlayerHouseButtonPanel = createJPanel(600,450,180,40,Color.black,true);
        exploreHayFieldButtonPanel = createJPanel(600,450,180,40,Color.black,true);
        taskScreenNextButtonPanel = createJPanel(600,420,180,40,Color.black,true);
        hayfieldNextButtonPanel = createJPanel(600,420,180,40,Color.black,true);
        findSaraButtonPanel = createJPanel(600,450,180,40,Color.black,true);
        findSuppliesButtonPanel = createJPanel(600,450,180,40,Color.black,true);
        exploreOldHouseSouthButtonPanel = createJPanel(600,450,180,40,Color.black,true);
        exploreRiverButtonPanel = createJPanel(600,450,180,40,Color.black,true);
        goToIslandButtonPanel = createJPanel(600,450,180,40,Color.black,true);
    }

    private void setAllButtons() {
        newGameButton = createJButton("New Game",100,50,false,Color.white,Color.black);
        quitGameButton = createJButton("Quit Game",100,50,false,Color.white,Color.black);
        playerPageEnterGameButton = createJButton("ENTER GAME",150,40,false, Color.white,Color.black);
        introScreenNextButton = createJButton("NEXT",150,40,false,Color.white,Color.black);
        userInputEnterButton = createJButton("ENTER",150,50,false,Color.white,Color.black);
        mapButton = createJButton("MAP",100,30,false,Color.white,Color.black);
        helpButton = createJButton("HELP",100,30,false,Color.white,Color.black);
        useButton = createJButton("use item",100,20,false,Color.white,Color.black);
        dropButton = createJButton("drop item",100,20,false,Color.white,Color.black);
        exploreAirportButton = createJButton("GO TO AIRPORT",180,40,false,Color.white,Color.black);
        explorePlayerHouseButton = createJButton("GO TO Player's House",180,40,false,Color.white,Color.black);
        exploreHayFieldButton = createJButton("GO TO Hay Field",180,40,false,Color.white,Color.black);
        arriveSpecialSceneNextButton = createJButton("NEXT",100,30,false,Color.white,Color.black);
        taskScreenNextButton = createJButton("GO TO TASK",180,40,false,Color.white,Color.black);
        hayfieldNextButton = createJButton("GO hay field",180,40,false,Color.white,Color.black);
        findSaraButton = createJButton("Look for Sara",180,40,false,Color.white,Color.black);
        findSuppliesButton = createJButton("Go Find Supplies",180,40,false,Color.white,Color.black);
        exploreOldHouseSouthButton = createJButton("Explore Old house South",180,40,false,Color.white,Color.black);
        exploreRiverButton = createJButton("Explore River",180,40,false,Color.white,Color.black);
        goToIslandButton = createJButton("Go To Island",180,40,false,Color.white,Color.black);
        musicButton = new JToggleButton("Music Off");
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

        askUserName.setText("\nPlease enter your name: \n");
        askUserName.setBackground(Color.black);
        askUserName.setForeground(Color.white);
        askUserName.setFont(new Font("Serif", Font.ITALIC, 24));
        askUserName.setEditable(false);
        userName.setPreferredSize(new Dimension(100,30));
        askForNamePanel.add(askUserName);
        askForNamePanel.add(userName);




        playerPageFooterPanel.add(musicButton);
        introScreenEnterGameButtonPanel.add(playerPageEnterGameButton);


        // Zoe notes: task --add function to playerPageEnterGameButton,
        // need to add introduction.txt to task1Screen mainTextPanel.
        // use writeToTextArea()
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
        locationDesc.setBackground(Color.black);
        locationDesc.setEditable(false);
        seeItem.setBackground(Color.black);
        seeItem.setForeground(Color.white);
        seeItem.setEditable(false);





        seePeople.setBackground(Color.black);
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
        directionButtonPanel.setLayout(new BorderLayout());


        northButton.setPreferredSize(new Dimension(36,54));
        northButton.setBorderPainted(false);
        northButton.setContentAreaFilled(false);
        northButton.setFocusPainted(false);
        northButton.setOpaque(false);



        southButton.setPreferredSize(new Dimension(36,54));
        southButton.setBorderPainted(false);
        southButton.setContentAreaFilled(false);
        southButton.setFocusPainted(false);
        southButton.setOpaque(false);

        eastButton.setPreferredSize(new Dimension(54,36));
        eastButton.setBorderPainted(false);
        eastButton.setContentAreaFilled(false);
        eastButton.setFocusPainted(false);
        eastButton.setOpaque(false);

        westButton.setPreferredSize(new Dimension(54,36));
        westButton.setBorderPainted(false);
        westButton.setContentAreaFilled(false);
        westButton.setFocusPainted(false);
        westButton.setOpaque(false);

        directionButtonPanel.add(northButton,BorderLayout.NORTH);
        directionButtonPanel.add(southButton,BorderLayout.SOUTH);
        directionButtonPanel.add(eastButton, BorderLayout.EAST);
        directionButtonPanel.add(westButton,BorderLayout.WEST);




        // inventory panel
        con.add(inventoryPanel);

        inventoryTextField.setText("**Your inventory**");
        inventoryTextField.setBackground(Color.black);
        inventoryTextField.setForeground(Color.white);
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
            con.add(findSaraButtonPanel);
            findSaraButtonPanel.add(findSaraButton);
        }
        else{
            JOptionPane.showMessageDialog(null,"You got caught! Game Over.","",JOptionPane.PLAIN_MESSAGE);
        }


        largeTextAreaPanel.add(largeTextArea);
        con.add(largeTextAreaPanel);
    }
    public static void taskPaddleRiverWithEnoughInventory() {
        if (player1.getPlayerInventory().contains("paddle")&&player1.getPlayerInventory().contains("raft") &&player1.getPlayerInventory().contains("shovel")){
            mainLocationDescPanel.setVisible(false);
            userInputPanel.setVisible(false);
            directionButtonPanel.setVisible(false);
            inventoryPanel.setVisible(false);
            clearZoneViewPanel();
            exploreOldHouseSouthButtonPanel.setVisible(false);
            taskScreen("scene-five.txt");
            con.add(exploreRiverButtonPanel);
            exploreRiverButtonPanel.setVisible(true);
            goToIslandButtonPanel.add(goToIslandButton);

            con.add(goToIslandButtonPanel);
            goToIslandButtonPanel.add(goToIslandButton);
            findSuppliesButtonPanel.setVisible(false);


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
                clearZoneViewPanel();
                taskScreen("win-text.txt");
                FileManager.sceneWriter(true, "sceneFivePassed");
                JOptionPane.showMessageDialog(null,"You won the game!", "", JOptionPane.PLAIN_MESSAGE);
                largeTextAreaPanel.add(largeTextArea);
                con.add(largeTextAreaPanel);
                largeTextArea.setText(FileManager.txtFileToString("win-text.txt"));
                goToIslandButtonPanel.setVisible(false);
                System.exit(0);
            }

        }
        else{

            JOptionPane.showMessageDialog(null,"You don't have enough items to go to island, please make sure you got paddle, shovel, food, and raft in your inventory and come back\n", "", JOptionPane.PLAIN_MESSAGE);
            mainTextPanel.setVisible(false);
            taskScreenNextButtonPanel.setVisible(false);
            largeTextAreaPanel.setVisible(false);
            locationImgPanel.setVisible(true);
            mainLocationDescPanel.setVisible(true);
            userInputPanel.setVisible(true);
            directionButtonPanel.setVisible(true);
            inventoryPanel.setVisible(true);
            goToIslandButtonPanel.setVisible(false);
        }

    }


    public static void clearZoneViewPanel() {

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




    public void exploreSpecialSceneClearedPanel(){
        exploreButtonPanel.add(exploreAirportButton);
        con.add(exploreButtonPanel);
    }

    public static void taskScreen(String taskFileName){
        largeTextAreaPanel.setVisible(false);
        con.add(mainTextPanel);
        con.add(taskScreenNextButtonPanel);

        mainTextPanel.setVisible(true);

        taskScreenNextButtonPanel.add(taskScreenNextButton);
        textField.setText(FileManager.txtFileToString(taskFileName));
        mainTextPanel.add(textField);

    }



}