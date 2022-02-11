package com.ChildrenOfSummer.SummerVacation.view;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    public TextField userNameTextField = new TextField("Enter Name", 20);
    public TextField userName = new TextField("Enter name", 20);
    private JTextArea textArea = new JTextArea(20, 20);
    private JTextArea askUserName = new JTextArea();
    public JTextArea textField = new JTextArea();
    public JButton newGameButton,loadGameButton, quitGameButton,playerPageEnterGameButton;
    private Container con;
    public JPanel titleNamePanel, newGameButtonPanel,askForNamePanel,playerPageFooterPanel,enterGameButtonPanel;
    public JLabel titleNameLabel;
    public JToggleButton musicButton;


    public MainFrame() {
        super("Summer Vacation");
        setUpMainMenu();
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
    }

    private void setAllButtons() {
        newGameButton = createJButton("New Game",150,50,false,Color.white,Color.black);
        loadGameButton = createJButton("Load Game",150,50,false,Color.white,Color.black);
        quitGameButton = createJButton("Quit Game",150,50,false,Color.white,Color.black);
        playerPageEnterGameButton = createJButton("ENTER GAME",150,50,false, Color.white,Color.black);
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


//        menuPanel.setVisible(true);
//        mainTextPanel.setVisible(false);
//        subPanel.setVisible(false);
//        displayPanel.setVisible(false);
//        userRewardsPanel.setVisible(false);
//        aboutTextPanel.setVisible(false);
//        howTextPanel.setVisible(false);
//        backButtonSubPanel.setVisible(false);
    }

    public void createGameScreen() {
        titleNamePanel.setVisible(true);
        newGameButtonPanel.setVisible(true);
        askForNamePanel.setVisible(false);
        playerPageFooterPanel.setVisible(false);
        enterGameButtonPanel.setVisible(false);
    }

    public void gameAboutScreen() {
//
//        aboutTextPanel.setBounds(250, 200, 700, 300);
//        aboutTextPanel.add(textField);
//        aboutTextPanel.setVisible(true);
//        titlePanel.setVisible(true);
//        menuPanel.setVisible(false);
//        mainTextPanel.setVisible(false);
//        subPanel.setVisible(false);
//        backButtonSubPanel.setVisible(true);
//        aboutLabel.setVisible(true);
    }

    public void gameHowScreen() {
//
//        howTextPanel.setBounds(100, 200, 700, 300);
//        howTextPanel.add(textField);
//        howTextPanel.setVisible(true);
//        titlePanel.setVisible(true);
//        menuPanel.setVisible(false);
//        mainTextPanel.setVisible(false);
//        subPanel.setVisible(false);
//        backButtonSubPanel.setVisible(true);
//        aboutLabel.setVisible(true);
    }

    public void writeToUserRewardsPanel(String string) {
//        userRewardsText.setText("Winnings: ");
//        userRewardsText.append(string);
//        userRewardsText.setFont(new Font("Arial", Font.BOLD, 15));
//        userRewardsText.setForeground(Color.RED);
//        userRewardsText.setBackground(Color.yellow);
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