        
/**
 * Main window class.
 *
 * Harvey Chamberlain
 * 18/6/2025
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class window extends JFrame implements ActionListener
{
    //Declare GUI variables
    JPanel mainPanel;
    
    JLabel displayMoney;
    JTextField moneyTextField;
    
    JButton upgradeDrillButton;
    JButton mineButton;
    
    JButton upgradeButtons;
    
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
        buttonPanel.setLayout(new GridLayout(0, 2, 10, 10)); // auto rows, and 2 collumns, with a 10px gap  
        buttonPanel.setBackground(Color.decode("#ADD8E6"));
        buttonPanel.setPreferredSize(new Dimension(400, 150));
        buttonPanel.setMaximumSize(new Dimension(400, 150));
        
        /*--PANEL CONTENT--*/
        Border border = BorderFactory.createLineBorder(Color.black,3); //Create a black border around the panel
        
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
        
        upgradeDrillButton = new JButton("Upgrade drill ($100)");
        upgradeDrillButton.addActionListener(this);
        upgradeDrillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Loops for upgrades
        JButton[] upgradeButtons = new JButton[5];
        String[] buttonLabels = {"Employ Miner ($10)", "Buy Factory", "Drill Oil", "Buy Truck Fleet", "Buy Chemical Plant"};
        for (int i=0; i<5; i++){
            upgradeButtons[i] = new JButton(buttonLabels[i]);
            upgradeButtons[i].addActionListener(this);
            buttonPanel.add(upgradeButtons[i]);
        }
            
        //Add GUI items to the panel
        
        mainPanel.add(labelPanel);
        mainPanel.add(mineButton);
        mainPanel.add(upgradeDrillButton);
        mainPanel.add(buttonPanel);
        
        
        labelPanel.add(displayMoney);
        labelPanel.add(displayPollution);
        
        mainPanel.setBorder(border);
        
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    
    MoneyManagement moneyManager = new MoneyManagement(); //create a moneymanagement object to track money on click
    UpgradeManagement upgradeManager = new UpgradeManagement(moneyManager); //create upgrademanagement object to handle purchasing upgrades
    PollutionManagement pollutionManager = new PollutionManagement(); //create a pollutionmanagement object to track pollution per click
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        //System.out.println(cmd);
        
        switch(cmd) {
            case "Mine":
                moneyManager.addClickMoney();
                pollutionManager.addClickPollution();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                displayPollution.setText("Pollution: " + pollutionManager.getPollution());
                break;
            case "Upgrade drill ($100)":
                upgradeManager.upgradeDrill(); //calls the upgrade drill method
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
            case "Employ Miner ($10)":
                upgradeManager.employMiner(); //calls the employMiner method from upgradeManagement
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
        }
    }
}
