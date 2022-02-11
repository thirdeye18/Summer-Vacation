package com.ChildrenOfSummer.SummerVacation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JFrame {

    public TextField userName = new TextField("", 20);
    public JTextField userInput = new JTextField(10);
    private JTextArea textArea = new JTextArea(20, 20);
    private JTextArea askUserName = new JTextArea();
    public JTextArea textField = new JTextArea();
    public JTextArea locationDesc = new JTextArea();
    public JTextArea askUserInput = new JTextArea();

    public JButton newGameButton,loadGameButton, quitGameButton,playerPageEnterGameButton,task1ScreenNextButton,userInputEnterButton,
            mapButton,helpButton,northButton,southButton,eastButton,westButton,dropButton,useButton;
    private Container con;
    public JPanel titleNamePanel, newGameButtonPanel,askForNamePanel,playerPageFooterPanel,enterGameButtonPanel,
            mainTextPanel,mainLocationDescPanel,userInputPanel,headerContentPanel,directionButtonPanel,
            inventoryPanel;
    public JLabel titleNameLabel,locationImgLabel;
    public JToggleButton musicButton;
    public BorderLayout setLayout;
    public DefaultListModel inventoryListModel;
    public JList inventoryList;


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
        newGameButton.addActionListener(e -> playerNameScreen());

        con.add(titleNamePanel);
        con.add(newGameButtonPanel);
        con.add(askForNamePanel);
        con.add(playerPageFooterPanel);
        con.add(enterGameButtonPanel);

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
        enterGameButtonPanel = createJPanel(600,400,200,100,Color.yellow,true);
        mainTextPanel = createJPanel(100,100,600,250,Color.yellow,true);
        mainLocationDescPanel = createJPanel(20,50,540,430,Color.red,true);
        userInputPanel = createJPanel(20,500,300,60,Color.green,true);
        headerContentPanel = createJPanel(0,0,800,50, Color.yellow,true);
        directionButtonPanel = createJPanel(630,300,100,120,Color.yellow,true);
        inventoryPanel = createJPanel(620,50,120,250,Color.yellow,true);
    }

    private void setAllButtons() {
        newGameButton = createJButton("New Game",150,50,false,Color.white,Color.black);
        loadGameButton = createJButton("Load Game",150,50,false,Color.white,Color.black);
        quitGameButton = createJButton("Quit Game",150,50,false,Color.white,Color.black);
        playerPageEnterGameButton = createJButton("ENTER GAME",150,50,false, Color.white,Color.black);
        task1ScreenNextButton = createJButton("NEXT",150,50,false,Color.white,Color.black);
        userInputEnterButton = createJButton("ENTER",150,50,false,Color.white,Color.black);
        mapButton = createJButton("MAP",150,50,false,Color.white,Color.black);
        helpButton = createJButton("HELP",150,50,false,Color.white,Color.black);
        northButton = createJButton("Go North",100,20,false,Color.white,Color.black);
        southButton = createJButton("Go South",100,20,false,Color.white,Color.black);
        westButton = createJButton("Go West",100,20,false,Color.white,Color.black);
        eastButton = createJButton("Go East",100,20,false,Color.white,Color.black);
        useButton = createJButton("use item",100,20,false,Color.white,Color.black);
        dropButton = createJButton("drop item",100,20,false,Color.white,Color.black);

    }
    public void createGameScreen() {
        titleNamePanel.setVisible(true);
        newGameButtonPanel.setVisible(true);
        askForNamePanel.setVisible(false);
        playerPageFooterPanel.setVisible(false);
        enterGameButtonPanel.setVisible(false);
    }

    public void playerNameScreen() {
        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel.setVisible(true);
        playerPageFooterPanel.setVisible(true);
        enterGameButtonPanel.setVisible(true);

        askUserName.setText("Please enter your name: ");
        askUserName.setBackground(Color.black);
        askUserName.setForeground(Color.white);
        askForNamePanel.add(askUserName);
        askForNamePanel.add(userName);

        musicButton = new JToggleButton("music on/off");
        playerPageFooterPanel.add(musicButton);
        enterGameButtonPanel.add(playerPageEnterGameButton);
        playerPageEnterGameButton.addActionListener(e -> task1Screen());

        // Zoe notes: task --add function to playerPageEnterGameButton,
        // need to add introduction.txt to task1Screen mainTextPanel.
        // use writeToTextArea()
    }

    //set up listeners
    private void setupListeners(){
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
    }

    public void task1Screen() {
        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel.setVisible(false);
        playerPageEnterGameButton.setVisible(false);
        mainTextPanel.add(textField);
        con.add(mainTextPanel);

        mainTextPanel.add(textField);
        enterGameButtonPanel.add(task1ScreenNextButton);
        task1ScreenNextButton.addActionListener(e -> zoneView());
    }

    public void zoneView() {


        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel.setVisible(false);
        playerPageEnterGameButton.setVisible(false);
        enterGameButtonPanel.setVisible(false);
        mainTextPanel.setVisible(false);


        //Location description panel.

        con.add(mainLocationDescPanel);
        // text description of current locatione

        // add a picture
        locationImgLabel = new JLabel();
        locationImgLabel.setBounds(20,120,540,300);
        locationImgLabel.setBackground(Color.BLUE);
        ImageIcon yourhouseImg = new ImageIcon("Assets/zone-png/zone2.png");
        locationImgLabel.setIcon(yourhouseImg);
        locationDesc.setText("This should be location desc");
        locationDesc.setForeground(Color.white);
        locationDesc.setBackground(Color.green);

        mainLocationDescPanel.add(locationImgLabel);
        mainLocationDescPanel.add(locationDesc);  // add "you are in xxxx, to the north is xxxx..."

        // UserInput panel, includes: text-What would you like to do? & textfield which takes user input & a ENTER button
        userInputPanel.setLayout(new GridLayout(3,1));
        // "What would you like to do?"
        askUserInput = new JTextArea("What would you like to do?");
        askUserInput.setEnabled(false);
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
      //  mapButton.addActionListener(displayMap);
        headerContentPanel.add(mapButton);


        //help button
      //  helpButton.addActionListener(displayImage);
        headerContentPanel.add(helpButton);

        // Direction buttons -North/South/West/East
        con.add(directionButtonPanel);

        directionButtonPanel.add(northButton);
        directionButtonPanel.add(southButton);
        directionButtonPanel.add(westButton);
        directionButtonPanel.add(eastButton);


        // inventory panel
        con.add(inventoryPanel);

        textField.setText("**Your inventory**");
        textField.setBackground(Color.yellow);
        inventoryPanel.add(textField);

        inventoryListModel = new DefaultListModel();
        // for iteration 1 demo purpose
        String items[]= {"bowtie","shovel","chips","letter",
                "itemA","itemB","ItemC","itemD","itemE"};
        inventoryList = new JList(items);
        inventoryList.setFixedCellWidth(80);

        JScrollPane pane = new JScrollPane(inventoryList);

        inventoryPanel.add(pane, BorderLayout.NORTH);
        inventoryPanel.add(useButton, BorderLayout.WEST);
        inventoryPanel.add(dropButton, BorderLayout.EAST);

    }




    public void writeToTextArea(String string) {
        textArea.setFont(new Font("Arial", Font.BOLD, 15));
        textArea.setPreferredSize(new Dimension(400, 75));
        textArea.setBackground(Color.yellow);
        textArea.setText(string);
    }

    public void writeToTextField(String string) {
        textField.setFont(new Font("Arial", Font.BOLD, 15));
        textField.setPreferredSize(new Dimension(700, 300));
        textField.setBackground(Color.yellow);
        textField.setText(string);
    }

    public void clearDisplayPanel() {
        //     displayPanel.removeAll();
    }

    public void displayCurtain() {
//        curtainLabel = new JLabel();
//        displayPanel.add(curtainLabel);
//        ImageIcon curtainIcon = new ImageIcon("resources/curtain1.png");
//        displayPanel.add(curtainLabel);
//        curtainLabel.setIcon(curtainIcon);
    }

    public void displayOpenedChest() {
//        chestOpenedLabel = new JLabel();
//        displayPanel.add(chestOpenedLabel);
//        ImageIcon chestOpened = new ImageIcon("resources/openedchest.png");
//        displayPanel.add(chestOpenedLabel);
//        chestOpenedLabel.setIcon(chestOpened);
    }

    public void displayClosedChest() {
//        chestClosedLabel = new JLabel();
//        displayPanel.remove(curtainLabel);
//        displayPanel.add(chestClosedLabel);
//        displayPanel.updateUI(); //gets the curtain to be removed
//        ImageIcon chestClosed = new ImageIcon("resources/closedchest.png");
//        displayPanel.add(chestClosedLabel);
//        chestClosedLabel.setIcon(chestClosed);
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

}
