package com.ChildrenOfSummer.SummerVacation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameGUI {
    JFrame window;
    Container con;
    JPanel titleNamePanel, newGameButtonPanel, askForNamePanel, playerPageFooterPanel, enterGameButtonPanel,
            mainTextPanel,headerContentPanel,directionButtonPanel,mainLocationDescPanel,userInputPanel;
    JLabel titleNameLabel,locationImgLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 40);    // customized font
    Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    JButton newGameButton,loadButton,quitButton, playerPageEnterGameButton, task1ScreenNextButton,inventoryButton,mapButton,
            helpButton,northButton,eastButton,westButton,southButton,userInputEnterButton;
    JToggleButton musicButton;
    JTextField userName,userInput;
    JTextArea askUserName,mainTextArea,askUserInput,locationDescText;


    TitleScreenHandler tsHandler = new TitleScreenHandler();
    playerPageEnterGameHandler enterHandler = new playerPageEnterGameHandler();
    task1ScreenNextButtonHandler taskNextHandler = new task1ScreenNextButtonHandler();
    displayImage displayImage = new displayImage();
    displayMap displayMap = new displayMap();
    //musicOnOffToggleButton musicHandler = new musicOnOffToggleButton();

    public static void main(String[] args) {
        new GameGUI();

    }
    public GameGUI(){
        window = new JFrame("Summer Vacation");
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // close the window
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);  // disabled default layout, we want to customize layout
        window.setVisible(true);  // make it appear on the screen
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,600,150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("SUMMER VACATION");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        newGameButtonPanel = new JPanel();
        newGameButtonPanel.setBounds(300,400,200,150);
        newGameButtonPanel.setBackground(Color.black);

        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.black);
        newGameButton.setForeground(Color.white);
        newGameButton.setFont(buttonFont);
        newGameButton.addActionListener(tsHandler);  // when click new game button, it will call actionlistener

        loadButton = new JButton("Load Game");
        loadButton.setBackground(Color.black);
        loadButton.setForeground(Color.white);
        loadButton.setFont(buttonFont);

        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.white);
        quitButton.setFont(buttonFont);

        titleNamePanel.add(titleNameLabel);
        newGameButtonPanel.add(newGameButton);
        newGameButtonPanel.add(loadButton);
        newGameButtonPanel.add(quitButton);

        con.add(titleNamePanel); // con: a base, you can put different things in the con.
        con.add(newGameButtonPanel);

    }

    public void playerNameScreen(){

        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel = new JPanel();
        askForNamePanel.setBounds(100,100,600, 250);
        askForNamePanel.setBackground(Color.black);
        askForNamePanel.setLayout(new GridLayout(2,1));
        con.add(askForNamePanel);


        askUserName = new JTextArea("Please enter your name: ");
        askUserName.setBounds(100,100,600,250);
        askUserName.setBackground(Color.black);
        askUserName.setForeground(Color.white);
        askUserName.setFont(buttonFont);

        userName = new JTextField(20);
        userName.setBackground(Color.black);
        userName.setForeground(Color.white);
        userName.setFont(buttonFont);

        askForNamePanel.add(askUserName);
        askForNamePanel.add(userName);

        playerPageFooterPanel = new JPanel();
        playerPageFooterPanel.setBounds(500,500,100,30);
        playerPageFooterPanel.setBackground(Color.black);
        con.add(playerPageFooterPanel);

        musicButton = new JToggleButton("music on/off");
        playerPageFooterPanel.add(musicButton);



        enterGameButtonPanel = new JPanel();
        enterGameButtonPanel.setBackground(Color.black);
        enterGameButtonPanel.setBounds(600,400,200,100);
        con.add(enterGameButtonPanel);


        playerPageEnterGameButton = new JButton("ENTER GAME");
        playerPageEnterGameButton.setFont(buttonFont);
        playerPageEnterGameButton.setBackground(Color.black);
        playerPageEnterGameButton.setForeground(Color.white);
        playerPageEnterGameButton.addActionListener(enterHandler);

        enterGameButtonPanel.add(playerPageEnterGameButton);

    }

    public void task1Screen(){

        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,600,250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea("This should be description of task 1");
        mainTextArea.setBounds(100,100,600,250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(buttonFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);


        task1ScreenNextButton = new JButton("NEXT");
        task1ScreenNextButton.setFont(buttonFont);
        task1ScreenNextButton.setBackground(Color.black);
        task1ScreenNextButton.setForeground(Color.white);
        task1ScreenNextButton.addActionListener(taskNextHandler);

        playerPageEnterGameButton.setVisible(false);
        enterGameButtonPanel.add(task1ScreenNextButton);


    }


    public void yourHouse(){

        titleNamePanel.setVisible(false);   // if we want to display new screen, we need to disable the previous screen first
        newGameButtonPanel.setVisible(false);
        askForNamePanel.setVisible(false);
        playerPageEnterGameButton.setVisible(false);
        enterGameButtonPanel.setVisible(false);
        mainTextPanel.setVisible(false);


        //Location description panel.
        mainLocationDescPanel = new JPanel();
        mainLocationDescPanel.setBounds(20,100,500,300);
        mainLocationDescPanel.setBackground(Color.white);
        con.add(mainLocationDescPanel);
        // text description of current location
        locationDescText = new JTextArea("SAMPLE TEXT: You are in your house.");

        // add a picture
        locationImgLabel = new JLabel();
        locationImgLabel.setBounds(20,150,500,300);
        //  ImageIcon yourhouseImg = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Assets/img/your_house.PNG")));
        //   locationImgLabel.setIcon(yourhouseImg);


        mainLocationDescPanel.add(locationDescText);
        mainLocationDescPanel.add(locationImgLabel);

        // UserInput panel, includes: text-What would you like to do? & textfield which takes user input & a ENTER button
        userInputPanel = new JPanel();
        userInputPanel.setBounds(20,450,300,60);
        userInputPanel.setBackground(Color.black);
        userInputPanel.setLayout(new GridLayout(3,1));
        // "What would you like to do?"
        askUserInput = new JTextArea("What would you like to do?");
        askUserInput.setEnabled(false);
        askUserInput.setBounds(20,450,300,60);
        askUserInput.setBackground(Color.black);
        askUserInput.setForeground(Color.white);
        askUserInput.setFont(buttonFont);
        con.add(userInputPanel);

        //user input textfield
        userInput = new JTextField(10);
        userInput.setBackground(Color.black);
        userInput.setForeground(Color.white);
        userInput.setFont(buttonFont);

        //"ENTER" button
        userInputEnterButton = new JButton("ENTER");
        // add all three components to userInputPanel
        userInputPanel.add(askUserInput);
        userInputPanel.add(userInput);
        userInputPanel.add(userInputEnterButton);



        // Headers - include inventory/map/help buttons
        headerContentPanel = new JPanel();
        headerContentPanel.setBackground(Color.black);
        headerContentPanel.setBounds(0,0,800,50);
        con.add(headerContentPanel);

        // inventory button
        inventoryButton = new JButton("INVENTORY");
        inventoryButton.setFont(buttonFont);
        inventoryButton.setBackground(Color.black);
        inventoryButton.setForeground(Color.white);
        inventoryButton.addActionListener(taskNextHandler);
        headerContentPanel.add(inventoryButton);

        // map button

        mapButton = new JButton("MAP");

        mapButton.setFont(buttonFont);
        mapButton.setBackground(Color.black);
        mapButton.setForeground(Color.white);
        mapButton.addActionListener(displayMap);
        headerContentPanel.add(mapButton);



        //map Image


        //help button
        helpButton = new JButton("HELP");
        helpButton.setFont(buttonFont);
        helpButton.setBackground(Color.black);
        helpButton.setForeground(Color.white);
        helpButton.addActionListener(displayImage);
        headerContentPanel.add(helpButton);

        // Direction buttons -North/South/West/East
        directionButtonPanel = new JPanel();
        directionButtonPanel.setBounds(600,300,100,120);
        directionButtonPanel.setBackground(Color.black);
        con.add(directionButtonPanel);

        northButton = new JButton("Go North");
        southButton = new JButton("Go South");
        westButton = new JButton("Go West");
        eastButton = new JButton("Go East");

        directionButtonPanel.add(northButton);
        directionButtonPanel.add(southButton);
        directionButtonPanel.add(westButton);
        directionButtonPanel.add(eastButton);



    }





    public class TitleScreenHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            playerNameScreen();
            // Input.playerCreator();
        }
    }
    public class playerPageEnterGameHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            task1Screen();

        }
    }

    public class task1ScreenNextButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            yourHouse();
        }
    }

    public class displayImage implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JFrame frame = new JFrame("Map");
            frame.setSize(400,400);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            ImageIcon helpIcon = new ImageIcon("Assets/img/help.PNG");
            JLabel label1 = new JLabel(helpIcon);
            frame.add(label1);
            frame.pack();
            frame.setVisible(true);
        }
    }

    public class displayMap implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JFrame frame = new JFrame("Map");
            frame.setSize(400,400);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            ImageIcon mapIcon = new ImageIcon("Assets/img/map.PNG");
            JLabel label = new JLabel(mapIcon);
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        }
    }


}