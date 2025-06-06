    
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
    
    JButton mineButton;
    
    JLabel displayMoney;
    JTextField moneyTextField;
    
    public window(){
        setTitle("ClickerGame");
        
        this.getContentPane().setPreferredSize(new Dimension(600,600));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /*--SETUP PANELS--*/
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        //(FlowLayout.CENTER, 400, 400));
        
        mainPanel.setBackground(Color.decode("#ADD8E6"));
        this.add(mainPanel, BorderLayout.CENTER);
        
        /*--PANEL CONTENT--*/
        mineButton = new JButton("Mine");
        mineButton.addActionListener(this);
        
        displayMoney = new JLabel("Money: ");
        moneyTextField = new JTextField(30);
        
        mainPanel.add(displayMoney);
        
        mainPanel.add(mineButton);
        
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    
    MoneyManagement manager = new MoneyManagement();
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        //System.out.println(cmd);
        
        switch(cmd) {
            case "Mine":
                manager.addClickMoney();
                displayMoney.setText("Money: $" + manager.getMoney());
        }
    }
}
