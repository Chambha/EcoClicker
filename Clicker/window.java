/**
 * Main window class for GUI and handing gameplay.
 *
 * Harvey Chamberlain
 * 6/8/2025
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class window extends JFrame implements ActionListener
{
    //Declare GUI variables
    JPanel mainPanel;
    
    JLabel displayMoney;
    JTextField moneyTextField;
    
    JButton upgradeDrillButton;
    JButton mineButton;
    JButton helpButton;
    
    JLabel displayPollution;
    JTextField pollutionTextField;
    
    JLabel moneyPerSec;
    JTextField moneyPerSecTextField;
    
    JProgressBar pollutionBar;
    
    private Timer guiTimer;
    
    public window(){
        gameOver = false;
        moneyManager = new MoneyManagement();
        pollutionManager = new PollutionManagement();
        upgradeManager = new UpgradeManagement(moneyManager, pollutionManager, this);
        
        setTitle("ClickerGame");
        
        this.getContentPane().setPreferredSize(new Dimension(500,500));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        /*--SETUP PANELS--*/
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout()); //set the layout to flow layout
        
        mainPanel.setBackground(Color.decode("#ADD8E6")); //sets the background to a light blue
        this.add(mainPanel, BorderLayout.CENTER); 
        
        //Creates a seperate panel for the labels
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(Color.decode("#ADD8E6"));

        //Creates a seperate panel for the upgrade buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2, 10, 10)); // auto rows, and 2 columns, with a 10px gap  
        buttonPanel.setBackground(Color.decode("#ADD8E6"));
        buttonPanel.setPreferredSize(new Dimension(400, 150));
        buttonPanel.setMaximumSize(new Dimension(400, 150));
        
        //Creates one more panel for green upgrades
        JPanel cleanPanel = new JPanel();
        cleanPanel.setLayout(new GridLayout(0, 2, 10, 10)); // auto rows, and 2 columns, with a 10px gap  
        cleanPanel.setBackground(Color.decode("#ADD8E6"));
        cleanPanel.setPreferredSize(new Dimension(400, 150));
        cleanPanel.setMaximumSize(new Dimension(400, 150));
        
        // Create labels for the upgrade sections
        JLabel industrialLabel = new JLabel("Industrial Upgrades");
        industrialLabel.setFont(new Font("Arial", Font.BOLD, 14));
        industrialLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel greenLabel = new JLabel("Green Upgrades");
        greenLabel.setFont(new Font("Arial", Font.BOLD, 14));
        greenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Container panels to hold the label and the buttons panel for industrial and green upgrades
        JPanel industrialPanel = new JPanel();
        industrialPanel.setLayout(new BoxLayout(industrialPanel, BoxLayout.Y_AXIS));
        industrialPanel.setBackground(Color.decode("#ADD8E6"));
        industrialPanel.add(industrialLabel);
        industrialPanel.add(Box.createRigidArea(new Dimension(0,5)));
        industrialPanel.add(buttonPanel);

        JPanel greenPanel = new JPanel();
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.Y_AXIS));
        greenPanel.setBackground(Color.decode("#ADD8E6"));
        greenPanel.add(greenLabel);
        greenPanel.add(Box.createRigidArea(new Dimension(0,5))); 
        greenPanel.add(cleanPanel);
        
        /*--PANEL CONTENT--*/
        Border border = BorderFactory.createLineBorder(Color.black,3); //Create a black border around the window
        
        //labels
        displayMoney = new JLabel("Money: ");
        moneyTextField = new JTextField(30);
        displayMoney.setFont(new Font("Arial", Font.BOLD, 12));
        
        displayPollution = new JLabel("Pollution: ");
        pollutionTextField = new JTextField(30);
        displayPollution.setFont(new Font("Arial", Font.BOLD, 12));
        
        JLabel moneyPerSec = new JLabel("Money per second: ");
        moneyPerSecTextField = new JTextField(30);
        moneyPerSec.setFont(new Font("Arial", Font.BOLD, 12));
        
        pollutionBar = new JProgressBar(); 
        pollutionBar.setMaximum(100000); //set max value to 100,000
        pollutionBar.setStringPainted(true); //allows progress bar to contain words
        pollutionBar.setString("Pollution:");
            
        //buttons
        ImageIcon mineIcon = new ImageIcon("images/pickaxe.jpg"); //Locate file path for the image
        Image scaledImage = mineIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH); //scale the image down
        mineButton = new JButton("");
        mineButton.setPreferredSize(new Dimension(50,40)); //set new button dimension
        mineButton.setIcon(new ImageIcon(scaledImage)); //set the button icon to the scaled image
        mineButton.addActionListener(this);
        mineButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mineButton.setToolTipText("Mine manually");
        
        upgradeDrillButton = new JButton("Upgrade drill ($100)");
        upgradeDrillButton.addActionListener(this);
        upgradeDrillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        upgradeDrillButton.setToolTipText("Increases money by +$1 every time you mine");
        
        helpButton = new JButton("?");
        helpButton.addActionListener(this);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Set ? button tool tip
        helpButton.setToolTipText("<html>Welcome to EcoClicker! Your goal is to reach $1,000,000 while staying in the pollution green zone under (25,000)<p>- Click the pickaxe to get money<p>- Buy industrial upgrades to generate money automatically, be careful though, they make a lot of pollution!<p>- Buy green upgrades to decrease pollution<p>- Stats are displayed when you hover your mouse over any upgrade");
        ToolTipManager.sharedInstance().setDismissDelay(30000000); //Makes tool tips stay visible 
        
        //Loop for upgrade buttons
        JButton[] upgradeButtons = new JButton[6];
        
        //Array of industrial button labels
        String[] buttonLabels = {"Employ Miner ($1,000)", "Truck Fleet ($5,000)", "Factory ($10,000)", 
        "Drill Oil ($15,000)", "Chemical Plant ($20,000)", "Research Lab ($100,000)"};
        
        //Array of tool tips
        String[] industrialToolTips = {"+$1 and +1 pollution per second", "+$30 and +5 pollution every 5 seconds", 
        "+$10 and +5 pollution per second", "+$50 and +10 pollution every 2 seconds", "+$100 and +10 pollution every 2 seconds", 
        "+$200 and +15 pollution per second"};
        for (int i=0; i<6; i++){
            upgradeButtons[i] = new JButton(buttonLabels[i]);
            upgradeButtons[i].setToolTipText(industrialToolTips[i]);
            upgradeButtons[i].addActionListener(this);
            buttonPanel.add(upgradeButtons[i]);
        }
            
        //Loop for clean energy buttons
        JButton[] cleanButtons = new JButton[6];
        
        //Array of green button labels
        String[] cleanLabels = {"Plant Tree ($100)", "Solar Panel ($500)", "Wind Turbine ($10,000)", 
        "Electric Dam ($20,000)", "Geothermal Plant ($50,000)", "Nuclear Reactor ($100,000)"};
        
        //Array of tool tips
        String[] greenToolTips = {"-1 pollution", "-1 pollution per second", "-20 pollutuon every 5 seconds",
        "-10 pollution every 2 seconds", "-15 pollution per second", "-35 pollution per second",};
        for (int i=0; i<6; i++){
            cleanButtons[i] = new JButton(cleanLabels[i]);
            cleanButtons[i].setToolTipText(greenToolTips[i]);
            cleanButtons[i].addActionListener(this);
            cleanPanel.add(cleanButtons[i]);
        }

        //Add GUI items to the panel
        mainPanel.add(labelPanel);
        mainPanel.add(mineButton);
        mainPanel.add(upgradeDrillButton);
        mainPanel.add(helpButton);
        
        // Add the containers with labels + buttons instead of raw button panels
        mainPanel.add(industrialPanel);
        mainPanel.add(greenPanel);
        
        labelPanel.add(displayMoney);
        labelPanel.add(moneyPerSec);
        labelPanel.add(pollutionBar);
        
        mainPanel.setBorder(border);
        
        this.pack();
        this.toFront();
        this.setVisible(true);
        
        guiTimer = new Timer(1000, new ActionListener(){ //every 1000ms update the money label and pollution bar
            public void actionPerformed(ActionEvent e){
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                
                moneyPerSec.setText("Money per sec: $" + upgradeManager.getMoneyPerSec());
                
                if(pollutionManager.getPollution() >= 25000 && pollutionManager.getPollution() < 50000){ 
                    //if pollution is between 25,000 and 50,000, progress bar is yellow
                    pollutionBar.setForeground(Color.YELLOW);
                } else if (pollutionManager.getPollution() >= 50000 && pollutionManager.getPollution() < 75000) {
                    //if pollution is between 50,000 and 75,000, progress bar is orange
                    pollutionBar.setForeground(Color.ORANGE);
                } else if (pollutionManager.getPollution() >= 75000 && pollutionManager.getPollution() < 100000) {
                    //if pollution is between 75,000 and 100,000, progress bar is red
                    pollutionBar.setForeground(Color.RED);
                } else { 
                    //anything below 25,000 pollution, progress bar is green
                    pollutionBar.setForeground(Color.GREEN);
                }
                
                pollutionBar.setValue(pollutionManager.getPollution());
                winCondition();
            }
        });
        guiTimer.start();
    }
    
    private boolean gameOver = false;
    
    public void restartGame(){
        gameOver = true;
        if(guiTimer != null){
            guiTimer.stop();
        }
        
        this.dispose(); //closes the current window
        new window(); //creates a new window
    }
    
    public void winCondition(){
        if (gameOver) return;
        
        if (pollutionManager.getPollution() >= 100000){
            gameOver = true; 
            guiTimer.stop();
            System.out.println("you lose");
            JOptionPane.showMessageDialog(this, "You lose! Pollution exceeded 100,000."); //game loss pop up
            restartGame();
            return;
        }
        
        if (pollutionManager.getPollution() <= 25000 && moneyManager.getMoney() >= 1000000){
            gameOver = true;
            guiTimer.stop();
            System.out.println("You win!");
            JOptionPane.showMessageDialog(this, 
            "You won! Succesfully obtained $1,000,000 dollars, and remained below 25000 pollution"); //game win pop up
            restartGame(); //Restarts game when you press ok
        }
    }
    
    window gameWindow = this;
    MoneyManagement moneyManager = new MoneyManagement(); //create a moneymanagement object to track money on click
    PollutionManagement pollutionManager = new PollutionManagement(); //create a pollutionmanagement object to track pollution per click
    UpgradeManagement upgradeManager = new UpgradeManagement(moneyManager, pollutionManager, this); //create upgrademanagement object to handle purchasing upgrades

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        
        switch(cmd) {
            case "": //Case for the main mine button as it has no label
                moneyManager.addClickMoney();
                pollutionManager.addClickPollution();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                winCondition();
                break;
                
            case "Upgrade drill ($100)":
                upgradeManager.upgradeDrill(); //calls the upgrade drill method
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                winCondition();
                break;
                
            case "Employ Miner ($1,000)":
                upgradeManager.employMiner(); //calls the employMiner method from upgradeManagement
                displayMoney.setText("Money: $" + moneyManager.getMoney()); 
                pollutionBar.setValue(pollutionManager.getPollution()); //updates the value of the pollution bar
                break;
                
            case "Truck Fleet ($5,000)":
                upgradeManager.buyTruckFleet(); 
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
                
            case "Factory ($10,000)":
                upgradeManager.buyFactory();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                winCondition();
                break;
                
            case "Drill Oil ($15,000)":
                upgradeManager.buyOilDrill(); 
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
            
            case "Chemical Plant ($20,000)":
                upgradeManager.buyChemicalPlant(); 
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
                
            case "Research Lab ($100,000)":
                upgradeManager.buyResearchLab();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
                
            case "Plant Tree ($100)":
                upgradeManager.plantTree();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
                
            case "Solar Panel ($500)":
                upgradeManager.buySolarPanel();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
            
            case "Wind Turbine ($10,000)":
                upgradeManager.buyWindTurbine();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
                
            case "Electric Dam ($20,000)":
                upgradeManager.buyDam();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
                
            case "Geothermal Plant ($50,000)":
                upgradeManager.buyGeoPlant();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
                
            case "Nuclear Reactor ($100,000)":
                upgradeManager.buyReactor();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                pollutionBar.setValue(pollutionManager.getPollution());
                break;
        }
    }
}

