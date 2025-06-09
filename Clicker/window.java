    
/**
 * Main window class.
 *
 * Harvey Chamberlain
 * 5/6/2025
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class window extends JFrame implements ActionListener
{
    //Declare GUI variables
    JPanel mainPanel;
    
    JButton upgradeDrillButton;
    JButton mineButton;
    
    JLabel displayMoney;
    JTextField moneyTextField;
    
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
        mineButton = new JButton("Mine");
        mineButton.addActionListener(this);
        
        displayMoney = new JLabel("Money: ");
        moneyTextField = new JTextField(30);
        
        upgradeDrillButton = new JButton("Upgrade drill ($100)");
        upgradeDrillButton.addActionListener(this);
        
        mainPanel.add(displayMoney);
        
        mainPanel.add(mineButton);
        mainPanel.add(upgradeDrillButton);

        
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    
    MoneyManagement moneyManager = new MoneyManagement(); //create a moneymanagement object to track money on click
    UpgradeManagement upgradeManager = new UpgradeManagement(moneyManager); //create upgrademanagement object to handle purchasing upgrades 
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        //System.out.println(cmd);
        
        switch(cmd) {
            case "Mine":
                moneyManager.addClickMoney();
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
            case "Upgrade drill ($100)":
                upgradeManager.upgradeDrill(); //calls the upgrade drill method
                displayMoney.setText("Money: $" + moneyManager.getMoney());
                break;
                
        }
    }
}
