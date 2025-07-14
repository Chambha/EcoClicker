/**
 * Main window class.
 *
 * Harvey Chamberlain
 * 3/7/2025
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
    
    JLabel displayPollution;
    JTextField pollutionTextField;
    
    public window(){
        setTitle("ClickerGame");
        
        this.getContentPane().setPreferredSize(new Dimension(500,500));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
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
        industrialPanel.add(Box.createRigidArea(new Dimension(0,5))); // small spacing
        industrialPanel.add(buttonPanel);

        JPanel greenPanel = new JPanel();
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.Y_AXIS));
        greenPanel.setBackground(Color.decode("#ADD8E6"));
        greenPanel.add(greenLabel);
        greenPanel.add(Box.createRigidArea(new Dimension(0,5))); // small spacing
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
            
        //buttons
        mineButton = new JButton("Mine");
        mineButton.addActionListener(this);
        mineButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mineButton.setToolTipText("Mine manually");
        
        upgradeDrillButton = new JButton("Upgrade drill ($100)");
        upgradeDrillButton.addActionListener(this);
        upgradeDrillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        upgradeDrillButton.setToolTipText("+$1 for every upgrade level");
        
        //Loop for upgrade buttons
        JButton[] upgradeButtons = new JButton[5];
        String[] buttonLabels = {"Employ Miner ($1,000)", "Truck Fleet ($5,000)", "Factory ($10,000)", 
        "Drill Oil ($15,000)", "Chemical Plant ($20,000)"};
        
        String[] industrialToolTips = {"+$1 and +1 pollution per second", "+$30 and +5 pollution every 5 seconds", 
        "+$10 and +5 pollution per second", "+$50 and +10 pollution every 2 seconds", "+$100 and +10 pollution every 2 seconds"};
        for (int i=0; i<5; i++){
            upgradeButtons[i] = new JButton(buttonLabels[i]);
            upgradeButtons[i].setToolTipText(industrialToolTips[i]);
            upgradeButtons[i].addActionListener(this);
            buttonPanel.add(upgradeButtons[i]);
        }
            
        //Loop for clean energy buttons
        JButton[] cleanButtons = new JButton[5];
        String[] cleanLabels = {"Plant Tree ($100)", "Solar Panel ($500)", "Wind Turbine ($10,000)", 
        "Hydroelectric Dam ($20,000)", "Nuclear Reactor ($100,000)"};
        
        String[] greenToolTips = {"-1 pollution", "-1 pollution per second", "-50 pollutuon every 5 seconds",
        "-10 pollution every 2 seconds", "-20 pollution per second"};
        for (int i=0; i<5; i++){
            cleanButtons[i] = new JButton(cleanLabels[i]);
            cleanButtons[i].setToolTipText(greenToolTips[i]);
            cleanButtons[i].addActionListener(this);
            cleanPanel.add(cleanButtons[i]);
        }

        //Add GUI items to the panel
        mainPanel.add(labelPanel);
        mainPanel.add(mineButton);
        mainPanel.add(upgradeDrillButton);
        
        // Add the containers with labels + buttons instead of raw button panels
        mainPanel.add(industrialPanel);
        mainPanel.add(greenPanel);
        
        labelPanel.add(displayMoney);
        labelPanel.add(displayPollution);
        
        mainPanel.setBorder(border);
        
        this.pack();
        this.toFront();
        this.setVisible(true);
        
        
        Timer guiTimer = new Timer(1000, new ActionListener(){ //every 1000ms update the money label
            public void actionPerformed(ActionEvent e){
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                displayPollution.setText("Pollution: " + pollutionManager.getPollution());
            }
        });
        guiTimer.start();
    }
    
    public void restartGame(){
        this.dispose(); //closes the current window
        new window(); //creates a new window
    }
    
    private boolean gameOver = false;
    
    public void winCondition(){
        if (pollutionManager.getPollution() > 100000){
            gameOver = true; 
            System.out.println("you lose");
            JOptionPane.showMessageDialog(this, "You lose! Pollution exceeded 100."); //game loss pop up
            restartGame();
        }
        
        if (pollutionManager.getPollution() <= 0 && moneyManager.getMoney() >= 1000000){
            gameOver = true;
            System.out.println("You win!");
            JOptionPane.showMessageDialog(this, 
            "You won! Succesfully obtained $1,000,000 dollars, and reduced to 0 pollution"); //game win pop up
            restartGame(); //Restarts game when you press ok
        }
    }
    
    window gameWindow = this;
    MoneyManagement moneyManager = new MoneyManagement(); //create a moneymanagement object to track money on click
    PollutionManagement pollutionManager = new PollutionManagement(); //create a pollutionmanagement object to track pollution per click
    UpgradeManagement upgradeManager = new UpgradeManagement(moneyManager, pollutionManager, this); //create upgrademanagement object to handle purchasing upgrades

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        //System.out.println(cmd);
        
        switch(cmd) {
            case "Mine":
                moneyManager.addClickMoney();
                pollutionManager.addClickPollution();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                displayPollution.setText("Pollution: " + pollutionManager.getPollution());
                winCondition();
                break;
                
            case "Upgrade drill ($100)":
                upgradeManager.upgradeDrill(); //calls the upgrade drill method
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                winCondition();
                break;
                
            case "Employ Miner ($1,000)":
                upgradeManager.employMiner(); //calls the employMiner method from upgradeManagement
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
                
            case "Truck Fleet ($5,000)":
                upgradeManager.buyTruckFleet(); //calls the buyTruckFleet method from upgradeManagement
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
                
            case "Factory ($10,000)":
                upgradeManager.buyFactory(); //calls the buyFactory method from upgradeManagement
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                winCondition();
                break;
                
            case "Drill Oil ($15,000)":
                upgradeManager.buyOilDrill(); //calls the buyFactory method from upgradeManagement
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
            
            case "Chemical Plant ($20,000)":
                upgradeManager.buyChemicalPlant(); //calls the buyChemicalPlant method from upgradeManagement
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
                
            case "Plant Tree ($100)":
                upgradeManager.plantTree();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
                
            case "Solar Panel ($500)":
                upgradeManager.buySolarPanel();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
            
            case "Wind Turbine ($10,000)":
                upgradeManager.buyWindTurbine();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
                
            case "Hydroelectric Dam ($20,000)":
                upgradeManager.buyDam();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
                
            case "Nuclear Reactor ($100,000)":
                upgradeManager.buyReactor();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
        }
    }
}

