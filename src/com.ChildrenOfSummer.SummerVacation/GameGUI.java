package com.ChildrenOfSummer.SummerVacation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    JFrame window;
    Container con;
    JPanel titleNamePanel, newGameButtonPanel, askForNamePanel, playerPageFooterPanel, enterGameButtonPanel,mainTextPanel;
    JLabel titleNameLabel; // JLable display text
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 40);    // customized font
    Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
    JButton newGameButton,loadButton,quitButton, playerPageEnterGameButton, task1ScreenNextButton;
    JToggleButton musicButton;
    JTextField userName = new JTextField();
    JTextArea askUserName,mainTextArea;


    TitleScreenHandler tsHandler = new TitleScreenHandler();
    playerPageEnterGameHandler enterHandler = new playerPageEnterGameHandler();
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
        mainTextPanel.setBackground(Color.blue);
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

        playerPageEnterGameButton.setVisible(false);
        enterGameButtonPanel.add(task1ScreenNextButton);


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
            // Input.playerCreator();
        }
    }




}