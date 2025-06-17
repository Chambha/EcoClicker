
/**
 * Class to handle upgrades
 *
 * Harvey Chamberlain
 * 18/6/2025
 */

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpgradeManagement
{
    private MoneyManagement moneyManager;
    private int drillCost = 100;
    
    private Timer incomeTimer;
    
    //miner upgrade
    private int miners = 0; 
    private int minerCost = 10;
    private int minerIncome = 1;
    private Timer minerTimer;

    
    public UpgradeManagement(MoneyManagement moneyManager){
        this.moneyManager = moneyManager;
    }
    
    public void upgradeDrill(){
        if(moneyManager.getMoney() >= drillCost){
            moneyManager.decreaseMoney(drillCost);
            moneyManager.increaseClickMoney();
            System.out.println("Drill upgraded");
        } else { 
            System.out.println("Not enough money for this upgrade!");
        }
    }
    
    /*-- Increase pollution upgrades --*/
    public void employMiner(){
        // int miners = 0; 
        // int minerCost = 10;
        // int minerIncome = 1;
        
        if(moneyManager.getMoney() >= minerCost){
            miners++;
            moneyManager.decreaseMoney(minerCost);
            System.out.println("Miner hired. Total employees: " + miners);
            
            if (minerTimer == null){
                minerTimer = new Timer(1000, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        moneyManager.increaseMoney(miners * minerIncome);
                    }
                });
                minerTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    /*-- Decrease pollution upgrades --*/
}
