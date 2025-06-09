
/**
 * Write a description of class Upgrade here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UpgradeManagement
{
    private MoneyManagement moneyManager;
    private int drillCost = 100;
    
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
}
