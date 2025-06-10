    
/**
 * Main window class.
 *
 * Harvey Chamberlain
 * 11/6/2025
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
    
    JButton upgradeDrillButton;
    JButton mineButton;
    
    JLabel displayMoney;
    JTextField moneyTextField;
    
    JLabel displayPollution;
    JTextField pollutionTextField;
    
    public window(){
        setTitle("ClickerGame");
        
        this.getContentPane().setPreferredSize(new Dimension(600,600));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /*--SETUP PANELS--*/
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout()); //set the layout to flow layout
        
        mainPanel.setBackground(Color.decode("#ADD8E6")); //sets the background to a light blue
        this.add(mainPanel, BorderLayout.CENTER); 
        
        /*--PANEL CONTENT--*/
        Border border = BorderFactory.createLineBorder(Color.black,3); //Create a black border around the panel
        
        mineButton = new JButton("Mine");
        mineButton.addActionListener(this);
        
        displayMoney = new JLabel("Money: ");
        moneyTextField = new JTextField(30);
        
        displayPollution = new JLabel("Pollution: ");
        pollutionTextField = new JTextField(30);
        
        upgradeDrillButton = new JButton("Upgrade drill ($100)");
        upgradeDrillButton.addActionListener(this);
        
        //Add GUI items to the panel
        mainPanel.add(displayMoney);
        mainPanel.add(displayPollution);
        mainPanel.setBorder(border);
        
        mainPanel.add(mineButton);
        mainPanel.add(upgradeDrillButton);

        
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
                
        }
    }
}
